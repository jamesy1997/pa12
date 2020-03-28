package es.udc.paproject.backend.rest.dtos;

public class CinemaDto {

	private Long id;
	private String cinemaName;

	public CinemaDto(Long id, String cinemaName) {
		this.id = id;
		this.cinemaName = cinemaName;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}
