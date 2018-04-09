package neu.edu.bean;

import java.util.ArrayList;
import java.util.List;

import neu.edu.entity.UserAccount;

public class UserSessionInfo {
	
	private Integer userId;
	private String name;
	private String role;	
	private List<UserProjectBean> userProjectBeans;
	private List<UserDonationBean> userDonationBeans;
	
	public UserSessionInfo(Integer userId) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		userProjectBeans = new ArrayList<>();
		userDonationBeans = new ArrayList<>();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<UserDonationBean> getUserDonationBeans() {
		return userDonationBeans;
	}

	public void setUserDonationBeans(List<UserDonationBean> userDonationBeans) {
		this.userDonationBeans = userDonationBeans;
	}

	public List<UserProjectBean> getUserProjectBeans() {
		return userProjectBeans;
	}

	public void setUserProjectBeans(List<UserProjectBean> userProjectBeans) {
		this.userProjectBeans = userProjectBeans;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
