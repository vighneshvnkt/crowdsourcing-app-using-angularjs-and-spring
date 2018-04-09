package neu.edu.controller.input;

public class DeleteProjectBean {
	private String projectName;
	private String projectDescription;
	 private String projectDeadline ;
	 private String amountRequired ;
	 private String amountReceived ;
	 private String projectCategory;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectDeadline() {
		return projectDeadline;
	}
	public void setProjectDeadline(String projectDeadline) {
		this.projectDeadline = projectDeadline;
	}
	public String getAmountRequired() {
		return amountRequired;
	}
	public void setAmountRequired(String amountRequired) {
		this.amountRequired = amountRequired;
	}
	public String getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}
	public String getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

}
