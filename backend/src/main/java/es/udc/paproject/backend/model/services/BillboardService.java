package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;

public interface BillboardService {

	public Block<BillboardItem<Session>> findSessions(LocalDateTime date, Cinema cinema)
			throws InstanceNotFoundException, NoRemainingSessionsException;

	Block<City> showCitys(int page, int size) throws InstanceNotFoundException;

	Block<Cinema> showCinemas(Long cityId, int page, int size) throws InstanceNotFoundException;
}
