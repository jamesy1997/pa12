package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {

	boolean existsById(Long sessionId);

}
