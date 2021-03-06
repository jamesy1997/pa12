package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.DateNotAllowedException;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieNotFoundException;
import es.udc.paproject.backend.model.exceptions.SessionNotFoundException;

public interface BillboardService {

	List<BillboardItem<Session>> findSessions(LocalDate date, Long cinemaId)
			throws InstanceNotFoundException, DateNotAllowedException;

	List<BillboardItem<Session>> TodaysBillboard(Long cinemaId) throws InstanceNotFoundException;

	List<City> showCities();

	List<Cinema> showCinemas(Long cityId);

	Movie findMovieDetail(Long movieId) throws MovieNotFoundException;

	Session findSessionDetail(Long sessionId, LocalDateTime localDateTime)
			throws SessionNotFoundException, ExpiratedSessionException;

}
