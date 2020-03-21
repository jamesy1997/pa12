package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	boolean existsById(Long sessionId);

//	@Query("SELECT s.Id FROM Session s JOIN Movie m ON s.MovieId = m.Id WHERE s.date BETWEEN startDate=?1 AND endDate=?2 ORDER BY m.Title")
//	public Slice<Session> getSessionByDateOrderByMovieTitle(LocalDateTime startDate, LocalDateTime endDate,
//			Pageable pageable);
}
