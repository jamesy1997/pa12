package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import es.udc.paproject.backend.model.exceptions.DateNotAllowedException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.BillboardItem;
import es.udc.paproject.backend.model.services.BillboardService;
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
	public void dateNotAllowedTest() throws InstanceNotFoundException, DateNotAllowedException {

		LocalDateTime date1 = LocalDateTime.of(2020, 5, 13, 20, 50);
		LocalDate date = LocalDate.of(2020, 1, 1);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);
		assertThrows(DateNotAllowedException.class, () -> billboardService.findSessions(date, cinema1.getId()));

	}

	@Test
	public void dateNotAllowedTest2() throws InstanceNotFoundException, DateNotAllowedException {

		LocalDateTime date1 = LocalDateTime.of(2020, 5, 13, 20, 50);
		LocalDate date = LocalDate.of(2020, 10, 3);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);
		assertThrows(DateNotAllowedException.class, () -> billboardService.findSessions(date, cinema1.getId()));

	}

	@Test
	public void findOneSessionTest() throws InstanceNotFoundException, DateNotAllowedException {

		LocalTime time = LocalTime.of(20, 50);
		LocalDate date = LocalDate.now();
		LocalDateTime date1 = LocalDateTime.of(date, time);

		LocalDateTime date2 = LocalDateTime.now();
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);
		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem1);

		assertEquals(billboard, billboardService.findSessions(date2.toLocalDate(), cinema1.getId()));

	}

	@Test
	public void findMoreMoviesTest() throws InstanceNotFoundException, DateNotAllowedException {

		LocalTime time = LocalTime.of(23, 00);
		LocalDate date = LocalDate.now();
		LocalDateTime date1 = LocalDateTime.of(date, time);

		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalTime time2 = LocalTime.of(23, 50);
		LocalDate localdate = LocalDate.now();
		LocalDateTime date2 = LocalDateTime.of(localdate, time2);

		Room room2 = new Room("room2", 100, cinema1);
		roomDao.save(room2);
		Movie movie2 = new Movie("movie2", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session2);

		BillboardItem<Session> billboardItem2 = new BillboardItem<>(session2.getMovie(), new ArrayList<>());
		billboardItem2.getItems().add(session2);

		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem1);
		billboard.add(billboardItem2);

		assertEquals(billboard, billboardService.findSessions(LocalDate.now(), cinema1.getId()));

	}

	@Test
	public void findMoreSessionsTest() throws InstanceNotFoundException, DateNotAllowedException {

		LocalTime time = LocalTime.of(23, 00);
		LocalDate date = LocalDate.now();
		LocalDateTime date1 = LocalDateTime.of(date, time);

		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalTime time2 = LocalTime.of(23, 50);
		LocalDate localdate2 = LocalDate.now();
		LocalDateTime date2 = LocalDateTime.of(localdate2, time2);

		Movie movie2 = new Movie("movie1", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session2);

		billboardItem1.getItems().add(session2);
		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem1);
		assertEquals(billboard, billboardService.findSessions(LocalDate.now(), cinema1.getId()));
	}

	@Test
	public void findMoreSessions2Test() throws InstanceNotFoundException, DateNotAllowedException {

		LocalTime time = LocalTime.of(23, 00);
		LocalDate date = LocalDate.now();
		LocalDateTime date1 = LocalDateTime.of(date, time);

		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalTime time2 = LocalTime.of(23, 59);
		LocalDate localdate = LocalDate.now();
		LocalDateTime date2 = LocalDateTime.of(localdate, time2);

		Movie movie2 = new Movie("movie1", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session2);

		billboardItem1.getItems().add(session2);
		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem1);

		List<BillboardItem<Session>> sessions = billboardService.findSessions(LocalDate.now(), cinema1.getId());

		assertEquals(sessions.size(), 1);
	}

	@Test
	public void findMoreSessions3Test() throws InstanceNotFoundException, DateNotAllowedException {

		// Prueba día diferente al actual, con la existencia de más sesiones que las que
		// se espera devolver
		LocalTime time = LocalTime.of(23, 00);
		LocalDate localdate = LocalDate.now().plusDays(1);
		LocalDateTime date1 = LocalDateTime.of(localdate, time);

		LocalDate date = LocalDate.now().plusDays(1);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);

		BillboardItem<Session> billboardItem1 = new BillboardItem<>(session1.getMovie(), new ArrayList<>());
		billboardItem1.getItems().add(session1);

		LocalTime time2 = LocalTime.of(00, 00);
		LocalDate localdate2 = LocalDate.now().plusDays(2);
		LocalDateTime date2 = LocalDateTime.of(localdate2, time2);

		Movie movie2 = new Movie("movie1", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room1, date2, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session2);

		billboardItem1.getItems().add(session2);
		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem1);

		LocalTime time3 = LocalTime.of(10, 00);
		LocalDate localdate3 = LocalDate.now().plusDays(2);
		LocalDateTime date3 = LocalDateTime.of(localdate3, time3);

		movieDao.save(movie2);
		Session session3 = new Session(movie2, room1, date3, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session3);

		billboardItem1.getItems().add(session3);
		billboard.add(billboardItem1);

		List<BillboardItem<Session>> sessions = billboardService.findSessions(date, cinema1.getId());

		assertEquals(sessions.size(), 1);
	}

	@Test
	public void findCinemaSessions() throws InstanceNotFoundException, DateNotAllowedException {

		LocalDateTime date1 = LocalDateTime.of(2020, 5, 13, 23, 00);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 5);
		movieDao.save(movie1);
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session1);

		LocalDateTime date2 = LocalDateTime.of(2020, 5, 13, 23, 50);
		City city2 = new City("City2");
		cityDao.save(city2);
		Cinema cinema2 = new Cinema("cinema2", city2);
		cinemaDao.save(cinema2);
		Room room2 = new Room("room2", 100, cinema2);
		roomDao.save(room2);
		Movie movie2 = new Movie("movie2", "summary", 5);
		movieDao.save(movie2);
		Session session2 = new Session(movie2, room2, date2, new BigDecimal(5), room1.getCapacity());
		sessionDao.save(session2);

		BillboardItem<Session> billboardItem2 = new BillboardItem<>(session2.getMovie(), new ArrayList<>());
		billboardItem2.getItems().add(session2);

		List<BillboardItem<Session>> billboard = new ArrayList<>();
		billboard.add(billboardItem2);

		assertEquals(billboard, billboardService.findSessions(LocalDate.now(), cinema2.getId()));

	}

	@Test
	public void showNoCitiesTest() throws InstanceNotFoundException {

		List<City> expectedCities = new ArrayList<>();

		assertEquals(expectedCities, billboardService.showCities());

	}

	@Test
	public void showCitiesTest() throws InstanceNotFoundException {

		City city1 = new City("City1");
		cityDao.save(city1);
		City city2 = new City("City2");
		cityDao.save(city2);
		List<City> expectedBlock = new ArrayList<>(Arrays.asList(city1, city2));

		assertEquals(expectedBlock, billboardService.showCities());
	}

	@Test
	public void showNoCinemasTest() throws InstanceNotFoundException {

		List<Cinema> expectedCinemas = new ArrayList<>();

		assertEquals(expectedCinemas, billboardService.showCinemas(null));

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

		List<Cinema> expectedBlock1 = new ArrayList<>(Arrays.asList(cinema1, cinema2));
		List<Cinema> expectedBlock2 = new ArrayList<>(Arrays.asList(cinema3));
		List<Cinema> expectedBlock3 = new ArrayList<>();

		assertEquals(expectedBlock1, billboardService.showCinemas(city1.getId()));
		assertEquals(expectedBlock2, billboardService.showCinemas(city2.getId()));
		assertEquals(expectedBlock3, billboardService.showCinemas(city3.getId()));
	}

}
