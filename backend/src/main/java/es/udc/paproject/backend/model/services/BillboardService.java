package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

public interface BillboardService {

	public Block<Session> findSessions(LocalDateTime date, int page, int size) throws InstanceNotFoundException;

}
