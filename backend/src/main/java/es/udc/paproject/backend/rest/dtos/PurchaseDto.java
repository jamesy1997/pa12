package es.udc.paproject.backend.rest.dtos;

public class PurchaseDto {

	private Long id;
	private Integer ticket;
	private Long date;
	private boolean pickedUp;
	private String username;
	private String cinemaName;

	public PurchaseDto() {

	}

	public PurchaseDto(Long id, Integer ticket, Long date, boolean pickedUp, String username, String cinemaName) {

		this.id = id;
		this.ticket = ticket;
		this.date = date;
		this.pickedUp = pickedUp;
		this.username = username;
		this.cinemaName = cinemaName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}
