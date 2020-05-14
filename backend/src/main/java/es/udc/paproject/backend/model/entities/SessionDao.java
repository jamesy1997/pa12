package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	boolean existsById(Long sessionId);

	@Query("SELECT s FROM Session s WHERE (s.date BETWEEN ?1 AND ?2) AND (s.room.cinema.id = ?3) ORDER BY s.movie.title, date")
	public List<Session> getTodaysSessionByDateOrderByMovieTitle(LocalDateTime startDate, LocalDateTime endDate,
			Long cinemaId);

	@Query("SELECT (s.room.capacity - ?2) AS remainingTickets FROM Session s WHERE s.id = ?1 ")
	public Integer getRemainingTickets(Long sessionId, Integer ticketsPurchased);

}
