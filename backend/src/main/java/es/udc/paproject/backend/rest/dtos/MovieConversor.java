package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;

public class MovieConversor {

	private MovieConversor() {
	}

	public final static MovieDto toMovieDto(Movie movie) {

		return new MovieDto(movie.getId(), movie.getTitle(), movie.getSummary(), movie.getDuration());
	}

	public final static Movie toMovie(MovieDto movieDto) {

		return new Movie(movieDto.getTitle(), movieDto.getSummary(), movieDto.getDuration());
	}

}
