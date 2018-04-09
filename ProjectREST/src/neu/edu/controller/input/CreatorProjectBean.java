package neu.edu.controller.input;



public class CreatorProjectBean {

	private String projectName;
	private String projectDescription;
	private String projectDeadline;
	private int amountRequired;
	private AdminCategoryBean projectCategory;
	
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
	
	public int getAmountRequired() {
		return amountRequired;
	}
	public void setAmountRequired(int amountRequired) {
		this.amountRequired = amountRequired;
	}
	
	public AdminCategoryBean getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(AdminCategoryBean projectCategory) {
		this.projectCategory = projectCategory;
	}
	public CreatorProjectBean() {
		projectCategory = new AdminCategoryBean();
	}
	
	
}
