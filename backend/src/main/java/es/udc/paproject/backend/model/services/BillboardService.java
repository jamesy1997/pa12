package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;

public interface BillboardService {

	public Block<BillboardItem<LocalDateTime>> findSessions(LocalDateTime date)
			throws InstanceNotFoundException, NoRemainingSessionsException;

}
