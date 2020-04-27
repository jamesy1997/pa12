package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.List;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;
import es.udc.paproject.backend.model.exceptions.NotFoundMovieException;
import es.udc.paproject.backend.model.exceptions.NotFoundSessionException;

public interface BillboardService {

	List<BillboardItem<Session>> findSessions(LocalDateTime date, Cinema cinema)
			throws InstanceNotFoundException, NoRemainingSessionsException;

	List<City> showCities();

	List<Cinema> showCinemas(Long cityId);

	Movie findMovie(Long movieId) throws NotFoundMovieException;

	Session findSession(Long sessionId, LocalDateTime localDateTime)
			throws NotFoundSessionException, ExpiratedSessionException;

	Cinema findCinema(Long cinemaId);
}
