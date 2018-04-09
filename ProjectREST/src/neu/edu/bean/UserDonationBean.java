package neu.edu.bean;

public class UserDonationBean {
	private UserProjectBean project;
	private int donation_amount;
	private CardInfoBean cardInfo;
	public CardInfoBean getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfoBean cardInfo) {
		this.cardInfo = cardInfo;
	}
	public UserProjectBean getProject() {
		return project;
	}
	public void setProject(UserProjectBean project) {
		this.project = project;
	}
	public int getDonation_amount() {
		return donation_amount;
	}
	public void setDonation_amount(int donation_amount) {
		this.donation_amount = donation_amount;
	}
	public UserDonationBean() {
		super();
		cardInfo = new CardInfoBean();
		project = new UserProjectBean();
	}
	

}
