package neu.edu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.CardInfoBean;
import neu.edu.bean.UserCategoryBean;
import neu.edu.bean.UserCommentsBean;
import neu.edu.bean.UserDonationBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.entity.ProjectCategory;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserComments;
import neu.edu.entity.UserCreditCard;
import neu.edu.entity.UserDonation;
import neu.edu.entity.UserProject;



@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public UserAccount validateUser(String username, String password) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserAccount where userName=:username and password=:password ");
		query.setString("username", username);
		query.setString("password",  password.hashCode()+"");

		UserAccount user = (UserAccount) query.uniqueResult();

		return user;

	}
	
	public UserAccount checkUser(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserAccount where userName=:username");
		query.setString("username", username);
//		query.setString("password", password);

		UserAccount user = (UserAccount) query.uniqueResult();

		return user;

	}
	
	
	@Transactional
	public UserAccount createUser(UserAccount userAccount) {
		Session session = sessionFactory.openSession();
		session.save(userAccount);
		return userAccount;
	}

	public UserAccount fetchUserAccount(Integer userId) {
		Session session = sessionFactory.openSession();
		return session.load(UserAccount.class, userId);
	}


	@Transactional
	public List<UserProject> getFullListOfProjects() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject").list();
	}
	
	@Transactional
	public List<UserCreditCard> getFullListOfCreditCards() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserCreditCard").list();
	}
	
	@Transactional
	public List<ProjectCategory> getFullListOfCategories() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from ProjectCategory").list();
	}
	
	@Transactional
	public List<UserProject> getAllProjects(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
	//	return session.createQuery(" from UserProject where userAccount.id = :userId").list();
		Query q = session.createQuery("from UserProject where userAccount.id = :userId");
		q.setInteger("userId", userId);
		return q.list();

	}
	
	@Transactional
	public boolean updateProject(UserProjectBean userProjectBean, Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		
		UserProject userProject = new UserProject();
		userProject.setAmountReceived(userProjectBean.getReceivedAmount());
		userProject.setAmountRequired(userProjectBean.getRequiredAmount());
	
		
ProjectCategory projectCategory = session.get(userProject.getProjectCategory().getClass(), userProject.getProjectCategory().getCategoryId());
		
		projectCategory.setCategoryDescription(userProjectBean.getCategory().getCatDescription());
		projectCategory.setCategoryName(userProjectBean.getCategory().getCatName());
		projectCategory.getUserAccount().setId(userProjectBean.getCategory().getUserId());
		userProject.setProjectCategory(projectCategory);
		
		//	userProject.getProjectCategory().setCategoryDescription(userProjectBean.getCategory().getCatDescription());
	//	userProject.getProjectCategory().setCategoryName(userProjectBean.getCategory().getCatName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (userProjectBean.getDeadline() != null) {
			try {
				userProject.setProjectDeadline(sdf.parse(userProjectBean.getDeadline()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		userProject.setProjectDescription(userProjectBean.getDesc());
		userProject.setProjectName(userProjectBean.getName());
		for(UserCommentsBean commentBean  :userProjectBean.getUserComments()){
			UserComments commentEntity = new UserComments();
			commentEntity.setComment(commentBean.getComment());
			if (commentBean.getComment_date() != null) {
				try {
					commentEntity.setCommentDate(sdf.parse(commentBean.getComment_date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(UserDonationBean donationBean: userProjectBean.getUserDonations()){
			UserDonation donationEntity = new UserDonation();
			donationEntity.setDonationAmount(donationBean.getDonation_amount());
			donationEntity.getUserCreditCard().setCardNumber(donationBean.getCardInfo().getCardNumber());
			donationEntity.getUserCreditCard().setCvv(donationBean.getCardInfo().getCvv());
			donationEntity.getUserCreditCard().setExpiry(donationBean.getCardInfo().getExpiryDate());
			userProject.getUserDonations().add(donationEntity);
		}
		
		session.saveOrUpdate(userProject);
		session.flush();

		return true;
	}
	
	@Transactional
	public boolean addProject(UserProjectBean userProjectBean,Integer userId) {
		
		// TODO Auto-generated method stub
		System.out.println("im am here to add project");
		Session session = sessionFactory.openSession();
		
	
		UserProject userProject = new UserProject();
		userProject.setAmountReceived(userProjectBean.getReceivedAmount());
		userProject.setAmountRequired(userProjectBean.getRequiredAmount());
		
		List<ProjectCategory> projectCategories = getFullListOfCategories();
		Integer categoryId = null;
		for(ProjectCategory categoryEntity: projectCategories){
			if(categoryEntity.getCategoryName().equals(userProjectBean.getCategory().getCatName())){
				categoryId = categoryEntity.getCategoryId();
			}
		}
		
		ProjectCategory projectCategory = session.get(ProjectCategory.class, categoryId);
		
		projectCategory.setCategoryDescription(userProjectBean.getCategory().getCatDescription());
		projectCategory.setCategoryName(userProjectBean.getCategory().getCatName());
		
		UserAccount userAccount = session.get(UserAccount.class, userId);
		projectCategory.setUserAccount(userAccount);
		projectCategory.getUserAccount().setId(userProjectBean.getCategory().getUserId());
		userProject.setProjectCategory(projectCategory);
	//	userAccount.getUserProjects().add(userProject);
		//userProject.getProjectCategory().setCategoryDescription(userProjectBean.getCategory().getCatDescription());
		//userProject.getProjectCategory().setCategoryName(userProjectBean.getCategory().getCatName());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (userProjectBean.getDeadline() != null) {
			try {
				userProject.setProjectDeadline(sdf.parse(userProjectBean.getDeadline()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		userProject.setUserAccount(userAccount);
		userProject.setProjectDescription(userProjectBean.getDesc());
		userProject.setProjectName(userProjectBean.getName());
		for(UserCommentsBean commentBean  :userProjectBean.getUserComments()){
			UserComments commentEntity = new UserComments();
			commentEntity.setComment(commentBean.getComment());
			if (commentBean.getComment_date() != null) {
				try {
					commentEntity.setCommentDate(sdf.parse(commentBean.getComment_date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		for(UserDonationBean donationBean: userProjectBean.getUserDonations()){
			UserDonation donationEntity = new UserDonation();
			donationEntity.setDonationAmount(donationBean.getDonation_amount());
			donationEntity.getUserCreditCard().setCardNumber(donationBean.getCardInfo().getCardNumber());
			donationEntity.getUserCreditCard().setCvv(donationBean.getCardInfo().getCvv());
			donationEntity.getUserCreditCard().setExpiry(donationBean.getCardInfo().getExpiryDate());
			userProject.getUserDonations().add(donationEntity);
		}
		
		session.save(userProject);
		//session.saveOrUpdate(projectCategory);
	//	session.delete(projectCategory);
		session.flush();
		return true;
	}

	
	@Transactional
	public boolean addCategory(UserCategoryBean userCategoryBean, Integer userId) {

		System.out.println("im am here to add project category");
		Session session = sessionFactory.openSession();
		
		ProjectCategory projectCategory = new ProjectCategory();
		projectCategory.setCategoryDescription(userCategoryBean.getCatDescription());
		projectCategory.setCategoryName(userCategoryBean.getCatName());
		UserAccount adminUser = session.get(UserAccount.class, userId);
		
		
		
		projectCategory.setUserAccount(adminUser);
		session.save(projectCategory);
		session.flush();
		return true;
		
	}
	
	@Transactional
	public boolean updateCategory(UserCategoryBean userCategoryBean, Integer userId) {

		System.out.println("im am here to update project category");
		Session session = sessionFactory.openSession();
		
		ProjectCategory projectCategory = new ProjectCategory();
		projectCategory.setCategoryDescription(userCategoryBean.getCatDescription());
		projectCategory.setCategoryName(userCategoryBean.getCatName());
		
		session.saveOrUpdate(projectCategory);
		session.flush();
		return true;
		
	}

	public boolean addDonation(UserDonationBean userDonationBean, Integer userId) {
		System.out.println("im am here to add donation");
		Session session = sessionFactory.openSession();
		
		Integer projectId = null;
		for(UserProject projectEntity: getFullListOfProjects()){
			if(projectEntity.getProjectName().equals(userDonationBean.getProject().getName())){
				projectId = projectEntity.getProjectId();
				break;
			}
		}
		UserProject donatedProject = session.get(UserProject.class, projectId);	
		System.out.println(projectId + "is the project id");
		UserAccount userAccount = session.get(UserAccount.class, userId);		
		UserDonation donationEntity = new UserDonation(userDonationBean.getDonation_amount());
		System.out.println(userId + "is the user id");
		Integer cardId = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		if (userDonationBean.getCardInfo().getExpiryDate() != null) {
			try {
				 d = sdf.parse(userDonationBean.getCardInfo().getExpiryDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(d);
		for(UserCreditCard cardEntity : getFullListOfCreditCards()){
			if(cardEntity.getCardNumber() == userDonationBean.getCardInfo().getCardNumber()
					&& cardEntity.getCvv() == userDonationBean.getCardInfo().getCvv()
					){
				cardId = cardEntity.getCardId();			
			}
		}
		System.out.println(cardId + "is the card id");
		if(cardId!=null){
			UserCreditCard cardEntity = session.get(UserCreditCard.class, cardId);
			donationEntity.setUserCreditCard(cardEntity);
			donatedProject.setAmountReceived(donatedProject.getAmountReceived() + userDonationBean.getDonation_amount());
			donationEntity.setUserProject(donatedProject);
			donationEntity.setUserAccount(userAccount);
			session.save(donationEntity);
			donatedProject.getUserDonations().add(donationEntity);
			session.save(donatedProject);
			System.out.println("donation added to db");
			session.flush();
			return true;
		}
		return false;
	}

	public boolean updateDonation(UserDonationBean userDonationBean, Integer userId) {
		System.out.println("im am here to add donation");
		Session session = sessionFactory.openSession();
		
		Integer projectId = null;
		for(UserProject projectEntity: getFullListOfProjects()){
			if(projectEntity.getProjectName().equals(userDonationBean.getProject().getName())){
				projectId = projectEntity.getProjectId();
				break;
			}
		}
		UserProject donatedProject = session.get(UserProject.class, projectId);	
		System.out.println(projectId + "is the project id");
		UserAccount userAccount = session.get(UserAccount.class, userId);		
		UserDonation donationEntity = new UserDonation(userDonationBean.getDonation_amount());
		System.out.println(userId + "is the user id");
		Integer cardId = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		if (userDonationBean.getCardInfo().getExpiryDate() != null) {
			try {
				 d = sdf.parse(userDonationBean.getCardInfo().getExpiryDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(d);
		for(UserCreditCard cardEntity : getFullListOfCreditCards()){
			if(cardEntity.getCardNumber() == userDonationBean.getCardInfo().getCardNumber()
					&& cardEntity.getCvv() == userDonationBean.getCardInfo().getCvv()
					){
				cardId = cardEntity.getCardId();			
			}
		}
		System.out.println(cardId + "is the card id");
		if(cardId!=null){
			UserCreditCard cardEntity = session.get(UserCreditCard.class, cardId);
			donationEntity.setUserCreditCard(cardEntity);
			donatedProject.setAmountReceived(donatedProject.getAmountReceived() + userDonationBean.getDonation_amount());
			donationEntity.setUserProject(donatedProject);
			donationEntity.setUserAccount(userAccount);
			session.saveOrUpdate(donationEntity);
			donatedProject.getUserDonations().add(donationEntity);
			session.saveOrUpdate(donatedProject);
			System.out.println("donation added to db");
			session.flush();
			return true;
		}
		return false;
	}

	public boolean addComment(UserCommentsBean userCommentBean, Integer userId) {
		System.out.println("im am here to add comment");
		Session session = sessionFactory.openSession();
		UserComments commentEntity = new UserComments();
		commentEntity.setComment(userCommentBean.getComment());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (userCommentBean.getComment_date() != null) {
			try {
				commentEntity.setCommentDate(sdf.parse(userCommentBean.getComment_date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		UserAccount userAccount = session.get(UserAccount.class, userId);
		commentEntity.setUserAccount(userAccount);
		System.out.println(userId + "is the user id");
		Integer projectId = null;
		for(UserProject projectEntity: getFullListOfProjects()){
			if(projectEntity.getProjectName().equals(userCommentBean.getProjectName())){
				projectId = projectEntity.getProjectId();
				break;
			}
		}
		UserProject donatedProject = session.get(UserProject.class, projectId);	
		System.out.println(projectId + "is the project id");
		commentEntity.setUserProject(donatedProject);
		session.save(commentEntity);
		System.out.println("donation added to db");
		session.flush();
		return true;
		
	}

	public boolean deleteProject(UserProjectBean userProjectBean, Integer integer) {
		System.out.println("im am here to delete project");
		Session session = sessionFactory.openSession();
		Integer projectId = null;
		for(UserProject projectEntity: getFullListOfProjects()){
			if(projectEntity.getProjectName().equals(userProjectBean.getName())){
				projectId = projectEntity.getProjectId();
				break;
			}
		}
		UserProject donatedProject = session.get(UserProject.class, projectId);
		System.out.println(projectId + "is the project id");
		for(UserDonation userDonation : donatedProject.getUserDonations()){
			session.delete(userDonation);
		}
		for(UserComments userComment : donatedProject.getUserCommentses()){
			session.delete(userComment);
		}
		session.delete(donatedProject);
		session.flush();
		return true;
	}
}
