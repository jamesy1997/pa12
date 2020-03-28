package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.Room;
import es.udc.paproject.backend.model.entities.RoomDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;
import es.udc.paproject.backend.model.services.BillboardItem;
import es.udc.paproject.backend.model.services.BillboardService;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BillboardServiceTest {

	@Autowired
	BillboardService billboardService;

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	@Autowired
	SessionDao sessionDao;

	@Autowired
	CityDao cityDao;

	@Autowired
	CinemaDao cinemaDao;

	@Autowired
	RoomDao roomDao;

	@Autowired
	MovieDao movieDao;

	@Test
	public void findNoSessionsTest() throws InstanceNotFoundException, NoRemainingSessionsException {

		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		assertThrows(NoRemainingSessionsException.class,
				() -> billboardService.findSessions(LocalDateTime.now(), cinema1));

	}

	@Test
	public void findOneSessionTest() throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDateTime date1 = LocalDateTime.of(2020, 3, 28, 23, 50);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5));
		sessionDao.save(session1);
		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		Block<BillboardItem<Session>> billboard = new Block<>(new ArrayList<>(), false);
		billboard.getItems().add(billboardItem1);

		assertEquals(billboard, billboardService.findSessions(LocalDateTime.now(), cinema1));

	}

	@Test
	public void findMoreMoviesTest() throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDateTime date1 = LocalDateTime.of(2020, 3, 28, 23, 00);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5));
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalDateTime date2 = LocalDateTime.of(2020, 3, 28, 23, 50);
//		City city2 = new City("City2");
//		cityDao.save(city2);
//		Cinema cinema2 = new Cinema("cinema2", city2);
//		cinemaDao.save(cinema2);
		Room room2 = new Room("room2", 100, cinema1);
		roomDao.save(room2);
		Movie movie2 = new Movie("movie2", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5));
		sessionDao.save(session2);

		BillboardItem<Session> billboardItem2 = new BillboardItem<>(session2.getMovie(), new ArrayList<>());
		billboardItem2.getItems().add(session2);

		Block<BillboardItem<Session>> billboard = new Block<>(new ArrayList<>(), false);
		billboard.getItems().add(billboardItem1);
		billboard.getItems().add(billboardItem2);
		System.out.println("Holaaaaaa");
		System.out.println("cartelera: " + billboard);
		Block<BillboardItem<Session>> actual = billboardService.findSessions(LocalDateTime.now(), cinema1);
		System.out.println("aqui esta: " + actual);

		assertEquals(billboard, billboardService.findSessions(LocalDateTime.now(), cinema1));

	}

	@Test
	public void findMoreSessionsTest() throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDateTime date1 = LocalDateTime.of(2020, 3, 28, 23, 00);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5));
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalDateTime date2 = LocalDateTime.of(2020, 3, 28, 23, 50);
//		City city2 = new City("City2");
//		cityDao.save(city2);
//		Cinema cinema2 = new Cinema("cinema2", city2);
//		cinemaDao.save(cinema2);
//		Room room2 = new Room("room2", 100, cinema1);
//		roomDao.save(room2);
		Movie movie2 = new Movie("movie1", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5));
		sessionDao.save(session2);

//		BillboardItem<Session> billboardItem2 = new BillboardItem<>(session2.getMovie(), new ArrayList<>());
//		billboardItem2.getItems().add(session2);

		billboardItem1.getItems().add(session2);
		Block<BillboardItem<Session>> billboard = new Block<>(new ArrayList<>(), false);
		billboard.getItems().add(billboardItem1);
//		billboard.getItems().add(billboardItem2);

		System.out.println("cartelera: " + billboard.toString());
		Block<BillboardItem<Session>> actual = billboardService.findSessions(LocalDateTime.now(), cinema1);
		System.out.println("sesiones encontradas: " + actual.toString());

		assertEquals(billboard, billboardService.findSessions(LocalDateTime.now(), cinema1));
	}

	@Test
	public void findCinemaSessions() throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDateTime date1 = LocalDateTime.of(2020, 3, 28, 23, 00);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5));
		sessionDao.save(session1);

		LocalDateTime date2 = LocalDateTime.of(2020, 3, 28, 23, 50);
		City city2 = new City("City2");
		cityDao.save(city2);
		Cinema cinema2 = new Cinema("cinema2", city2);
		cinemaDao.save(cinema2);
		Room room2 = new Room("room2", 100, cinema2);
		roomDao.save(room2);
		Movie movie2 = new Movie("movie2", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room2, date2, new BigDecimal(5));
		sessionDao.save(session2);

		BillboardItem<Session> billboardItem2 = new BillboardItem<>(session2.getMovie(), new ArrayList<>());
		billboardItem2.getItems().add(session2);

		Block<BillboardItem<Session>> billboard = new Block<>(new ArrayList<>(), false);
		billboard.getItems().add(billboardItem2);

		assertEquals(billboard, billboardService.findSessions(LocalDateTime.now(), cinema2));

	}

	@Test
	public void showNoCitiesTest() throws InstanceNotFoundException {

		Block<City> expectedCities = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedCities, billboardService.showCitys(0, 1));

	}

	@Test
	public void showCitiesTest() throws InstanceNotFoundException {

		City city1 = new City("City1");
		cityDao.save(city1);
		City city2 = new City("City2");
		cityDao.save(city2);
		Block<City> expectedBlock = new Block<>(Arrays.asList(city1, city2), false);

		assertEquals(expectedBlock, billboardService.showCitys(0, 2));
	}

	@Test
	public void showNoCinemasTest() throws InstanceNotFoundException {

		Block<Cinema> expectedCinemas = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedCinemas, billboardService.showCinemas(null, 0, 1));

	}

	@Test
	public void showCinemasTest() throws InstanceNotFoundException {

		City city1 = new City("City1");
		cityDao.save(city1);
		City city2 = new City("City2");
		cityDao.save(city2);
		City city3 = new City("City3");
		cityDao.save(city3);

		Cinema cinema1 = new Cinema("Cinema1", city1);
		cinemaDao.save(cinema1);
		Cinema cinema2 = new Cinema("Cinema2", city1);
		cinemaDao.save(cinema2);

		Cinema cinema3 = new Cinema("Cinema3", city2);
		cinemaDao.save(cinema3);

		Block<Cinema> expectedBlock1 = new Block<>(Arrays.asList(cinema1, cinema2), false);
		Block<Cinema> expectedBlock2 = new Block<>(Arrays.asList(cinema3), false);
		Block<Cinema> expectedBlock3 = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedBlock1, billboardService.showCinemas(city1.getId(), 0, 2));
		assertEquals(expectedBlock2, billboardService.showCinemas(city2.getId(), 0, 1));
		assertEquals(expectedBlock3, billboardService.showCinemas(city3.getId(), 0, 1));
	}

}
