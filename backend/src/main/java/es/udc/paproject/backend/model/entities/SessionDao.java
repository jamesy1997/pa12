package es.udc.paproject.backend.model.entities;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	boolean existsById(Long sessionId);

	@Query("SELECT s.Id FROM Session s JOIN Movie m ON s.MovieId = m.Id WHERE s.date BETWEEN startDate=?1 AND endDate=?2 ORDER BY m.Title")
	public Slice<Session> getSessionByDateOrderByMovieTitle(LocalDateTime startDate, LocalDateTime endDate,
			Pageable pageable);
}
