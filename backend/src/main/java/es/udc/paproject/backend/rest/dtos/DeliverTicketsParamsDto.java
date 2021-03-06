package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeliverTicketsParamsDto {

	private Integer creditCard;
	private Long purchaseId;

	public DeliverTicketsParamsDto() {
	}

	public DeliverTicketsParamsDto(Long purchaseId, Integer creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	@NotNull
	@Size(min = 8, max = 16)
	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

}
