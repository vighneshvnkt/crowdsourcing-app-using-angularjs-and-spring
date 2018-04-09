package neu.edu.bean;

import java.util.ArrayList;
import java.util.List;

public class UserProjectBean {
	
	private String name;
	private String desc;
	private String deadline;
	private int requiredAmount;
	private int receivedAmount;
	private UserCategoryBean category;
	private List<UserCommentsBean> userComments;
	private List<UserDonationBean> userDonations;
	
	public List<UserCommentsBean> getUserComments() {
		return userComments;
	}



	public void setUserComments(List<UserCommentsBean> userComments) {
		this.userComments = userComments;
	}



	public String getDeadline() {
		return deadline;
	}



	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}



	public int getRequiredAmount() {
		return requiredAmount;
	}



	public void setRequiredAmount(int requiredAmount) {
		this.requiredAmount = requiredAmount;
	}



	public int getReceivedAmount() {
		return receivedAmount;
	}



	public void setReceivedAmount(int receivedAmount) {
		this.receivedAmount = receivedAmount;
	}



	public UserCategoryBean getCategory() {
		return category;
	}



	public void setCategory(UserCategoryBean category) {
		this.category = category;
	}



	public List<UserDonationBean> getUserDonations() {
		return userDonations;
	}



	public void setUserDonations(List<UserDonationBean> userDonations) {
		this.userDonations = userDonations;
	}



	public UserProjectBean() {
		// TODO Auto-generated constructor stub
		userComments = new ArrayList<>();
		category = new UserCategoryBean();
		userDonations = new ArrayList<>();
	}
	
	
	
	



	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
