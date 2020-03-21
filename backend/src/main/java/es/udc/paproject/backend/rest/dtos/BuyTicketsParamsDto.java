package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BuyTicketsParamsDto {

	private Integer ticket;
	private Integer creditCard;

	public BuyTicketsParamsDto(Integer ticket, Integer creditCard) {

		this.ticket = ticket;
		this.creditCard = creditCard;
	}

	@NotNull
	@Min(value = 1)
	@Max(value = 10)
	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	@NotNull
	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

}
