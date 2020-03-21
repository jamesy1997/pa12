package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class PurchaseSummaryDto {

	private Long purchaseDate;
	private String title;
	private Integer ticket;
	private BigDecimal totalPrice;
	private Long sessionDate;

	public PurchaseSummaryDto() {
	}

	public PurchaseSummaryDto(Long purchaseDate, String title, Integer ticket, BigDecimal totalPrice,
			Long sessionDate) {

		this.purchaseDate = purchaseDate;
		this.title = title;
		this.ticket = ticket;
		this.totalPrice = totalPrice;
		this.sessionDate = sessionDate;
	}

	public Long getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Long sessionDate) {
		this.sessionDate = sessionDate;
	}

}
