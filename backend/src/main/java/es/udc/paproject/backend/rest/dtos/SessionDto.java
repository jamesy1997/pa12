package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class SessionDto {

	private String movieTitle;
	private Integer duration;
	private BigDecimal price;
	private Long date;
	private String roomName;
	private Integer capacityLeft;

	public SessionDto() {
	}

	public SessionDto(String movieTitle, Integer duration, BigDecimal price, Long date, String roomName,
			Integer capacityLeft) {

		this.movieTitle = movieTitle;
		this.duration = duration;
		this.price = price;
		this.date = date;
		this.roomName = roomName;
		this.capacityLeft = capacityLeft;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getCapacityLeft() {
		return capacityLeft;
	}

	public void setCapacityLeft(Integer capacityLeft) {
		this.capacityLeft = capacityLeft;
	}

}
