package neu.edu.entity;
// Generated 24 Apr, 2017 7:02:22 PM by Hibernate Tools 5.2.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * UserAccount generated by hbm2java
 */
public class UserAccount implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String userType;
	private String firstName;
	private String lastName;
	private String password;
	private Date dob;
	private String mobile;
	private String email;
	private String title;
	private Set<UserComments> userCommentses = new HashSet<UserComments>(0);
	private Set<ProjectCategory> projectCategories = new HashSet<ProjectCategory>(0);
	private Set<UserProject> userProjects = new HashSet<UserProject>(0);
	private Set<UserDonation> userDonations = new HashSet<UserDonation>(0);
	private Set<UserCreditCard> userCreditCards = new HashSet<UserCreditCard>(0);

	public UserAccount() {
	}

	public UserAccount(String userName, String userType) {
		this.userName = userName;
		this.userType = userType;
	}

	public UserAccount(String userName, String userType, String firstName, String lastName, String password, Date dob,
			String mobile, String email, String title, Set<UserComments> userCommentses,
			Set<ProjectCategory> projectCategories, Set<UserProject> userProjects, Set<UserDonation> userDonations,
			Set<UserCreditCard> userCreditCards) {
		this.userName = userName;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dob = dob;
		this.mobile = mobile;
		this.email = email;
		this.title = title;
		this.userCommentses = userCommentses;
		this.projectCategories = projectCategories;
		this.userProjects = userProjects;
		this.userDonations = userDonations;
		this.userCreditCards = userCreditCards;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<UserComments> getUserCommentses() {
		return this.userCommentses;
	}

	public void setUserCommentses(Set<UserComments> userCommentses) {
		this.userCommentses = userCommentses;
	}

	public Set<ProjectCategory> getProjectCategories() {
		return this.projectCategories;
	}

	public void setProjectCategories(Set<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public Set<UserProject> getUserProjects() {
		return this.userProjects;
	}

	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}

	public Set<UserDonation> getUserDonations() {
		return this.userDonations;
	}

	public void setUserDonations(Set<UserDonation> userDonations) {
		this.userDonations = userDonations;
	}

	public Set<UserCreditCard> getUserCreditCards() {
		return this.userCreditCards;
	}

	public void setUserCreditCards(Set<UserCreditCard> userCreditCards) {
		this.userCreditCards = userCreditCards;
	}

}
