package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaDao extends PagingAndSortingRepository<Cinema, Long> {

	Slice<Cinema> findByCityId(Long cityId, Pageable pageable);

}
