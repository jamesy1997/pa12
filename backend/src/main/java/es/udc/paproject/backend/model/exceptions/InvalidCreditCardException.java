package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidCreditCardException extends Exception {

	private Integer creditCard;

	public InvalidCreditCardException(Integer creditCard) {
		super("Tickets were not purchased with this credit card number: " + creditCard);
		this.creditCard = creditCard;
	}

	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

}
