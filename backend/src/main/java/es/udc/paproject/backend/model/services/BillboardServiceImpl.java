package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.DateNotAllowedException;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieNotFoundException;
import es.udc.paproject.backend.model.exceptions.SessionNotFoundException;

@Service
@Transactional
public class BillboardServiceImpl implements BillboardService {

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private MovieDao movieDao;

	@Override
	public List<BillboardItem<Session>> findSessions(LocalDate date, Long cinemaId)
			throws InstanceNotFoundException, DateNotAllowedException {

		List<BillboardItem<Session>> billboard;
		if (date.equals(null))
			billboard = findTodaysSessions(LocalDateTime.now(), cinemaId);

		if (date.isAfter(LocalDateTime.now().plusDays(7).toLocalDate())
				|| date.isBefore(LocalDateTime.now().toLocalDate())) {
			throw new DateNotAllowedException();
		}

		LocalDateTime dateNotToday = date.atStartOfDay();
		billboard = findTodaysSessions(dateNotToday, cinemaId);
		return billboard;
	}

	private List<BillboardItem<Session>> findTodaysSessions(LocalDateTime date, Long cinemaId)
			throws InstanceNotFoundException {

		LocalDate today = date.toLocalDate();
		LocalTime todaysLimit = LocalTime.of(23, 59);
		LocalDateTime endDate = LocalDateTime.of(today, todaysLimit);

		List<Session> sessionList = sessionDao.getTodaysSessionByDateOrderByMovieTitle(date, endDate, cinemaId);

		List<BillboardItem<Session>> billboard = new ArrayList<>();

		ListIterator<Session> listIterator = sessionList.listIterator();

		Movie currentMovie = null;
		BillboardItem<Session> currentBillboardItem = null;

		while (listIterator.hasNext()) {

			Session session1 = listIterator.next();

			if (currentMovie == null) {

				currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
				currentBillboardItem.getItems().add(session1);
				billboard.add(currentBillboardItem);
				currentMovie = session1.getMovie();

			} else if (currentMovie.getTitle() != session1.getMovie().getTitle()) {

				currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
				currentBillboardItem.getItems().add(session1);
				billboard.add(currentBillboardItem);
				currentMovie = session1.getMovie();

			} else {

				currentBillboardItem.getItems().add(session1);

			}

		}
		return billboard;

	}

	@Override
	public List<City> showCities() {

		Iterable<City> cities = cityDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<City> citiesAsList = new ArrayList<>();
		cities.forEach(c -> citiesAsList.add(c));
		return citiesAsList;
	}

	@Override
	public List<Cinema> showCinemas(Long cityId) {

		Iterable<Cinema> cinemas = cinemaDao.findByCityIdOrderByName(cityId);
		List<Cinema> cinemasAsList = new ArrayList<>();

		cinemas.forEach(c -> cinemasAsList.add(c));

		return cinemasAsList;
	}

	@Override
	@Transactional(readOnly = true)
	public Movie findMovie(Long movieId) throws MovieNotFoundException {

		Optional<Movie> movie = movieDao.findById(movieId);

		if (!movie.isPresent()) {

			throw new MovieNotFoundException();
		} else {

			return movie.get();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Session findSessionDetail(Long sessionId, LocalDateTime localDateTime)
			throws SessionNotFoundException, ExpiratedSessionException {

		Optional<Session> session = sessionDao.findById(sessionId);

		if (!session.isPresent()) {

			throw new SessionNotFoundException();

		} else if (session.get().getDate().isBefore(localDateTime)) {

			throw new ExpiratedSessionException(sessionId);

		} else {

			return session.get();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cinema findCinema(Long cinemaId) {

		Optional<Cinema> cinema = cinemaDao.findById(cinemaId);

		return cinema.get();
	}

}
