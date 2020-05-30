package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class PurchaseSummaryDto {

	private Long id;
	private Long purchaseDate;
	private String title;
	private Integer ticket;
	private BigDecimal totalPrice;
	private Long sessionDate;
	private String cinemaName;

	public PurchaseSummaryDto() {
	}

	public PurchaseSummaryDto(Long id, Long purchaseDate, String title, Integer ticket, BigDecimal totalPrice,
			Long sessionDate, String cinemaName) {

		this.id = id;
		this.purchaseDate = purchaseDate;
		this.title = title;
		this.ticket = ticket;
		this.totalPrice = totalPrice;
		this.sessionDate = sessionDate;
		this.cinemaName = cinemaName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}
