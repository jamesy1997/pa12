package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class DeliverTicketsParamsDto {

	private Integer creditCard;

	public DeliverTicketsParamsDto() {
	}

	public DeliverTicketsParamsDto(Long purchaseId, Integer creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

}
