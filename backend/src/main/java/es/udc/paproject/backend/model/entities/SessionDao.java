package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	boolean existsById(Long sessionId);

	@Query("SELECT s FROM Session s WHERE (s.date BETWEEN ?1 AND ?2) AND (s.room.cinema = ?3) ORDER BY s.movie.title, date")
	public Slice<Session> getSessionByDateOrderByMovieTitle(LocalDateTime startDate, LocalDateTime endDate,
			Cinema cinema);

}
