package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;
import es.udc.paproject.backend.model.services.ShoppingManagementService;
import es.udc.paproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShoppingManagementServiceTest {

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

	@Autowired
	ShoppingManagementService shoppingManagementService;

	private User signUpUser(String userName) {

		User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");

		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}

		return user;
	}

	@Test
	public void buyTicketsTest() throws InstanceNotFoundException, PermissionRoleException, ExpiratedSessionException,
			NotEnoughTicketsException {

		LocalDateTime date1 = LocalDateTime.of(2021, 03, 03, 11, 25);
		City city1 = new City("City1");
		cityDao.save(city1);
		Cinema cinema1 = new Cinema("cinema1", city1);
		cinemaDao.save(cinema1);
		Room room1 = new Room("room1", 100, cinema1);
		roomDao.save(room1);
		Movie movie1 = new Movie("movie1", "summary", 120);
		movieDao.save(movie1);
		User user1 = signUpUser("user1");
		Session session1 = new Session(movie1, room1, date1, new BigDecimal(5));
		sessionDao.save(session1);

		shoppingManagementService.buyTickets(session1, 5, 123456789, user1.getId());
		assertEquals(95, session1.getRoom().getCapacity());

		LocalDateTime date2 = LocalDateTime.of(2019, 03, 03, 11, 25);
		City city2 = new City("City2");
		cityDao.save(city2);
		Cinema cinema2 = new Cinema("cinema2", city2);
		cinemaDao.save(cinema2);
		Room room2 = new Room("room2", 100, cinema2);
		roomDao.save(room2);
		Movie movie2 = new Movie("movie2", "summary", 120);
		movieDao.save(movie2);
		User user2 = signUpUser("user2");
		Session session2 = new Session(movie2, room2, date2, new BigDecimal(5));
		sessionDao.save(session2);

		assertThrows(ExpiratedSessionException.class,
				() -> shoppingManagementService.buyTickets(session2, 5, 123456789, user2.getId()));

		LocalDateTime date3 = LocalDateTime.of(2021, 03, 03, 11, 25);
		City city3 = new City("City3");
		cityDao.save(city3);
		Cinema cinema3 = new Cinema("cinema3", city3);
		cinemaDao.save(cinema3);
		Room room3 = new Room("room3", 100, cinema3);
		roomDao.save(room3);
		Movie movie3 = new Movie("movie3", "summary", 120);
		movieDao.save(movie3);
		User user3 = signUpUser("user3");
		Session session3 = new Session(movie3, room3, date3, new BigDecimal(5));
		sessionDao.save(session3);

		assertThrows(NotEnoughTicketsException.class,
				() -> shoppingManagementService.buyTickets(session3, 105, 123456789, user3.getId()));

		assertThrows(NotEnoughTicketsException.class,
				() -> shoppingManagementService.buyTickets(session3, -4, 123456789, user3.getId()));
	}

}
