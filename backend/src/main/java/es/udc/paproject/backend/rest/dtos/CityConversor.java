package es.udc.paproject.backend.rest.dtos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;

public class CityConversor {

	private CityConversor() {
	}

	private final static CityDto toCityDto(City city) {

		List<CinemaDto> cinemas = city.getCinemas().stream().map(c -> toCinemaDto(c)).collect(Collectors.toList());

		cinemas.sort(Comparator.comparing(CinemaDto::getCinemaName));
		return new CityDto(city.getId(), city.getName(), cinemas);
	}

	private final static CinemaDto toCinemaDto(Cinema cinema) {

		return new CinemaDto(cinema.getId(), cinema.getName());
	}

	public final static List<CinemaDto> toCinemaDtos(List<Cinema> cinemas) {

		return cinemas.stream().map(c -> toCinemaDto(c)).collect(Collectors.toList());
	}

	public final static List<CityDto> toCityDtos(List<City> cities) {
		return cities.stream().map(c -> toCityDto(c)).collect(Collectors.toList());
	}

}
