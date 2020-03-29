package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class BillboardParamsDto {

	private Long cityId;
	private Long cinemaId;
	private LocalDateTime date;

	public BillboardParamsDto(LocalDateTime date, Long cityId, Long cinemaId) {
		this.date = date;
		this.cityId = cityId;
		this.cinemaId = cinemaId;
	}

	@NotNull
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@NotNull
	public Long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}

	@NotNull
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
