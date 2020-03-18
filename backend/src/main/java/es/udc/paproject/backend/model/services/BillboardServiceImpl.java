package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

public class BillboardServiceImpl implements BillboardService {

	@Autowired
	private SessionDao sessionDao;

	@Override
	public Block<Session> findSessions(LocalDateTime date, int page, int size) throws InstanceNotFoundException {

		Iterable<Session> sesiones = sessionDao.findAll();

		return null;
	}

}
