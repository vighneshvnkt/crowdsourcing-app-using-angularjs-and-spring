package neu.edu.controller.input;

public class DonorDonationBean {
	
	private int donationAmount;
	private String projectName;
	private String expiryDate;
	private int cvv;
	private int cardNumber;
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getDonationAmount() {
		return donationAmount;
	}
	public void setDonationAmount(int donationAmount) {
		this.donationAmount = donationAmount;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public DonorDonationBean() {
		super();
		
	}

}
