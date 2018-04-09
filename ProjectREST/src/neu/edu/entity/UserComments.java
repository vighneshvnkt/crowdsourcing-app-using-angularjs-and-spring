package neu.edu.entity;
// Generated 24 Apr, 2017 7:02:22 PM by Hibernate Tools 5.2.1.Final

import java.util.Date;

/**
 * UserComments generated by hbm2java
 */
public class UserComments implements java.io.Serializable {

	private Integer commentId;
	private UserAccount userAccount;
	private UserProject userProject;
	private String comment;
	private Date commentDate;

	public UserComments() {
	}

	public UserComments(String comment) {
		this.comment = comment;
	}

	public UserComments(UserAccount userAccount, UserProject userProject, String comment, Date commentDate) {
		this.userAccount = userAccount;
		this.userProject = userProject;
		this.comment = comment;
		this.commentDate = commentDate;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserProject getUserProject() {
		return this.userProject;
	}

	public void setUserProject(UserProject userProject) {
		this.userProject = userProject;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
