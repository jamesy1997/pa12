package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class CityDto {

	private Long id;
	private String cityName;
	private List<CinemaDto> cinemas;

	public CityDto() {
	}

	public CityDto(Long id, String cityName, List<CinemaDto> cinemas) {
		this.id = id;
		this.cityName = cityName;
		this.cinemas = cinemas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<CinemaDto> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<CinemaDto> cinemas) {
		this.cinemas = cinemas;
	}

}
