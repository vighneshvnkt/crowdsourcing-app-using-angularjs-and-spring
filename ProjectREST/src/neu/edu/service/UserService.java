package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.UserCommentsBean;
import neu.edu.bean.UserDonationBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.bean.UserSessionInfo;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserComments;
import neu.edu.entity.UserCreditCard;
import neu.edu.entity.UserDonation;
import neu.edu.entity.UserProject;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	
	public Integer validateUser(String username,String password){
		System.out.println("Service is called Called");
		UserAccount user = userDao.validateUser(username,password);
		
		if (user == null) {
			System.out.println("User Not Found");
			return null;
		} else {
			System.out.println("User  Found");
			return user.getId();
		}
	}

	public UserSessionInfo fetchUserAccountDetails(Integer userId) {
		// TODO Auto-generated method stub
		UserSessionInfo userSessionInfo=null;
		
		UserAccount userAccount = userDao.fetchUserAccount(userId);
		if(userAccount!=null){
			userSessionInfo = new UserSessionInfo(userAccount.getId());
			userSessionInfo.setName(userAccount.getFirstName() + ' ' + userAccount.getLastName());
			userSessionInfo.setRole(userAccount.getUserType());
		}
		if(userAccount.getUserProjects()!=null){
		for(UserProject userProject:userAccount.getUserProjects()){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.getCategory().setCatName(userProject.getProjectCategory().getCategoryName());
			userProjectBean.getCategory().setCatDescription(userProject.getProjectCategory().getCategoryDescription());
			userProjectBean.getCategory().setUserId(userProject.getProjectCategory().getUserAccount().getId());
			userProjectBean.setDeadline(userProject.getProjectDeadline().toString());
			userProjectBean.setDesc(userProject.getProjectDescription());
			userProjectBean.setName(userProject.getProjectName());
			userProjectBean.setReceivedAmount(userProject.getAmountReceived());
			userProjectBean.setRequiredAmount(userProject.getAmountRequired());
			if(userProject.getUserCommentses()!= null){
			for(UserComments commentEntity: userProject.getUserCommentses()){
				UserCommentsBean commentBean = new UserCommentsBean();
				commentBean.setComment(commentEntity.getComment());
				commentBean.setComment_date(commentEntity.getCommentDate().toString());
				userProjectBean.getUserComments().add(commentBean);
			}
			}
		/*	if(userProject.getUserDonations()!=null){
			for(UserDonation donationEntity: userProject.getUserDonations()){
				UserDonationBean donationBean = new UserDonationBean(userProjectBean);
				donationBean.setDonation_amount(donationEntity.getDonationAmount());
				donationBean.getCardInfo().setCardNumber(donationEntity.getUserCreditCard().getCardNumber());
				donationBean.getCardInfo().setCvv(donationEntity.getUserCreditCard().getCvv());
				donationBean.getCardInfo().setExpiryDate(donationEntity.getUserCreditCard().getExpiry());
				
				userProjectBean.getUserDonations().add(donationBean);
			}
			} */
			userSessionInfo.getUserProjectBeans().add(userProjectBean);
		}
	}
		return userSessionInfo;
	}
	
	@Transactional
	public List<UserProjectBean> getFullListOfProject( ) {
		// TODO Auto-generated method stub
		List<UserProject> userProjects = userDao.getFullListOfProjects();
		
		List<UserProjectBean> response = new ArrayList<>();
		for(UserProject userProject:userProjects){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.setName(userProject.getProjectName());
			userProjectBean.setDesc(userProject.getProjectDescription());
			userProjectBean.getCategory().setCatName(userProject.getProjectCategory().getCategoryName());
			userProjectBean.getCategory().setCatDescription(userProject.getProjectCategory().getCategoryDescription());
			userProjectBean.getCategory().setUserId(userProject.getProjectCategory().getUserAccount().getId());
			userProjectBean.setDeadline(userProject.getProjectDeadline().toString());
			userProjectBean.setReceivedAmount(userProject.getAmountReceived());
			userProjectBean.setRequiredAmount(userProject.getAmountRequired());
			if(userProject.getUserCommentses()!=null){
			for(UserComments entityComment: userProject.getUserCommentses()){
				UserCommentsBean userBeanComment = new UserCommentsBean();
				userBeanComment.setComment(entityComment.getComment());
				userBeanComment.setComment_date(entityComment.getCommentDate().toString());
				userProjectBean.getUserComments().add(userBeanComment);
			}
			}
			/*if(userProject.getUserDonations()!=null){
			for(UserDonation donationEntity: userProject.getUserDonations()){
				UserDonationBean donationBean = new UserDonationBean(userProjectBean);
				donationBean.setDonation_amount(donationEntity.getDonationAmount());
				donationBean.getCardInfo().setCardNumber(donationEntity.getUserCreditCard().getCardNumber());
				donationBean.getCardInfo().setCvv(donationEntity.getUserCreditCard().getCvv());
				donationBean.getCardInfo().setExpiryDate(donationEntity.getUserCreditCard().getExpiry());
				
				userProjectBean.getUserDonations().add(donationBean);
			}
			}*/
			
			response.add(userProjectBean);
		}
		return response;
	}

	

	
	

}
