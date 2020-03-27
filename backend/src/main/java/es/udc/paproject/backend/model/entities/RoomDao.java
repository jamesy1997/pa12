package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomDao extends PagingAndSortingRepository<Room, Long> {

	Slice<Room> findByCinemaId(Long cinemaId, Pageable pageable);

}
