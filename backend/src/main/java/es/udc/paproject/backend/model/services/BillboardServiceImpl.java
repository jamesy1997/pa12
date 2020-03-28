package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;

@Service
@Transactional
public class BillboardServiceImpl implements BillboardService {

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Override
	public Block<BillboardItem<Session>> findSessions(LocalDateTime date, Cinema cinema)
			throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDate today = date.toLocalDate();
		LocalTime todaysLimit = LocalTime.of(23, 59);
		LocalDateTime endDate = LocalDateTime.of(today, todaysLimit);
//		Long cinemaId = cinema.getId();

		Slice<Session> sessions = sessionDao.getSessionByDateOrderByMovieTitle(date, endDate, cinema);

		List<Session> sessionList = sessions.getContent();

		Block<BillboardItem<Session>> billboard = new Block<>(new ArrayList<>(), sessions.hasNext());

		ListIterator<Session> listIterator = sessionList.listIterator();

		if (sessionList.isEmpty()) {

			throw new NoRemainingSessionsException();

		} else {

			Movie currentMovie = null;
			BillboardItem<Session> currentBillboardItem = null;

			while (listIterator.hasNext()) {

				Session session1 = listIterator.next();

				if (currentMovie == null) {

					currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
					currentBillboardItem.getItems().add(session1);
					billboard.getItems().add(currentBillboardItem);
					currentMovie = session1.getMovie();

				} else if (currentMovie.getTitle() != session1.getMovie().getTitle()) {

					currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
					currentBillboardItem.getItems().add(session1);
					billboard.getItems().add(currentBillboardItem);
					currentMovie = session1.getMovie();

				} else {

					currentBillboardItem.getItems().add(session1);
					// int index = billboard.getItems().indexOf(currentBillboardItem);
					// billboard.getItems().get(index).getItems().add(session1);

				}

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

	public List<Cinema> showCinemas(Long cityId) {

		Iterable<Cinema> cinemas = cinemaDao.findByCityId(cityId);
		List<Cinema> cinemasAsList = new ArrayList<>();

		cinemas.forEach(c -> cinemasAsList.add(c));

		return cinemasAsList;
	}

}
