package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.List;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;

public interface BillboardService {

	Block<BillboardItem<Session>> findSessions(LocalDateTime date, Cinema cinema)
			throws InstanceNotFoundException, NoRemainingSessionsException;

	List<City> showCities();

	public BillboardCinema showBillboardCinema(LocalDateTime date, Cinema cinema)
			throws InstanceNotFoundException, NoRemainingSessionsException;

	List<Cinema> showCinemas(Long cityId);

	Movie findMovie(Long movieId);

	Session findSession(Long sessionId);

	Cinema findCinema(Long cinemaId);
}
