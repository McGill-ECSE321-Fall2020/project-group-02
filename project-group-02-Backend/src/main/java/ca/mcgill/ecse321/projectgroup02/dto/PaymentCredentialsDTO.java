package ca.mcgill.ecse321.projectgroup02.dto;

public class PaymentCredentialsDTO{
	private String cardHolderName;
	private String ccNumber;
	private String expirationDate;
	private String cvc;
	
	public PaymentCredentialsDTO() {}
	
	public PaymentCredentialsDTO(String cardHolderName, String ccNumber, String expirationDate, String cvc) {
		this.cardHolderName = cardHolderName;
		this.ccNumber = ccNumber;
		this.expirationDate = expirationDate;
		this.cvc = cvc;
	}

	public String getCardHolderName() {
		return this.cardHolderName;
	}

	public String getCcNumber() {
		return this.ccNumber;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public String getCvc() {
		return this.cvc;
	}

}
