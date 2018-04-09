package neu.edu.bean;

public class CardInfoBean {

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
	public CardInfoBean(String expiryDate, int cvv, int cardNumber) {
		super();
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.cardNumber = cardNumber;
	}
	public CardInfoBean() {
		// TODO Auto-generated constructor stub
	}
}
