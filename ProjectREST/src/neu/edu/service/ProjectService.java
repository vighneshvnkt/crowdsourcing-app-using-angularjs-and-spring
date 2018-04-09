package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.UserCommentsBean;
import neu.edu.bean.UserDonationBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserComments;
import neu.edu.entity.UserDonation;
import neu.edu.entity.UserProject;

@Service
public class ProjectService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public boolean addProject(UserProjectBean userProjectBean,Integer userId){
		return userDAO.addProject(userProjectBean, userId);
	}

	public boolean updateProject(UserProjectBean userProjectBean, Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.updateProject(userProjectBean, userId);
	}
	
	@Transactional
	public List<UserProjectBean> getAllProject( Integer userId) {
		// TODO Auto-generated method stub
		List<UserProject> userProjects = userDAO.getAllProjects(userId);
		
		List<UserProjectBean> response = new ArrayList<>();
		if(userProjects!=null){
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
			response.add(userProjectBean);
		}
		}
		return response;
	}
	
	

}
