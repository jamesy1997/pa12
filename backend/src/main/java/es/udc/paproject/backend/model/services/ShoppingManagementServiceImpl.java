package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.model.entities.PurchaseDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;

@Service
@Transactional
public class ShoppingManagementServiceImpl implements ShoppingManagementService {

	@Autowired
	private PermissionChecker permissionChecker;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public Long buyTickets(Session session, Integer tickets, Integer creditCard, Long userId)
			throws InstanceNotFoundException, PermissionRoleException, ExpiratedSessionException,
			NotEnoughTicketsException {

		User user = permissionChecker.checkSpectator(userId);
		if (!sessionDao.existsById(session.getId())) {
			throw new InstanceNotFoundException("Session not found:", session);
		}

		if (session.getDate().isBefore(LocalDateTime.now())) {
			throw new ExpiratedSessionException(session.getId());
		}

		if ((tickets < 1) || (tickets > session.getRoom().getCapacity())) {
			throw new NotEnoughTicketsException(tickets);
		}

		Purchase purchase = new Purchase(session, tickets, creditCard, LocalDateTime.now(), false, user);
		session.getRoom().setCapacity(session.getRoom().getCapacity() - tickets);
		purchase = purchaseDao.save(purchase);
		return purchase.getId();

	}

}
