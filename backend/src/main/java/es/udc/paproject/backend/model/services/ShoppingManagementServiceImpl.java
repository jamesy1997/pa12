package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.model.entities.PurchaseDao;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidCreditCardException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.TicketsAlreadyPickedUpException;

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
	public Purchase buyTickets(Session session, Integer tickets, Integer creditCard, Long userId)
			throws InstanceNotFoundException, ExpiratedSessionException, NotEnoughTicketsException {

		User user = permissionChecker.checkUser(userId);
		if (!sessionDao.existsById(session.getId())) {
			throw new InstanceNotFoundException("Session not found:", session);
		}

		if (session.getDate().isBefore(LocalDateTime.now())) {
			throw new ExpiratedSessionException(session.getId());
		}

		if (((tickets < 1) || (tickets > 10)) || (tickets > session.getRoom().getCapacity())) {
			throw new NotEnoughTicketsException(tickets);
		}

		Purchase purchase = new Purchase(session, tickets, creditCard, LocalDateTime.now(), false, user);
		session.getRoom().setCapacity(session.getRoom().getCapacity() - tickets);
		purchase = purchaseDao.save(purchase);
		return purchase;
	}

	@Override
	public Block<Purchase> showPurchases(Long userId, int page, int size) throws InstanceNotFoundException {

		permissionChecker.checkUser(userId);
		Slice<Purchase> slice = purchaseDao.findByUserIdOrderByDateAsc(userId, PageRequest.of(page, size));

		return new Block<>(slice.getContent(), slice.hasNext());

	}

	@Override
	public boolean deliverTickets(Long userId, Long purchaseId, Integer creditCard) throws InstanceNotFoundException,
			ExpiratedSessionException, InvalidCreditCardException, TicketsAlreadyPickedUpException {

		permissionChecker.checkUser(userId);
		Optional<Purchase> optPurchase;
		Purchase purchase;
		optPurchase = purchaseDao.findById(purchaseId);

		try {
			purchase = optPurchase.get();
		} catch (NoSuchElementException e) {
			throw new InstanceNotFoundException("Purchase not found with id: ", purchaseId);
		}

		if (purchase.getSession().getDate().isBefore(LocalDateTime.now())) {
			throw new ExpiratedSessionException(purchase.getSession().getId());
		}

		if (purchase.getCreditCard() != creditCard) {
			throw new InvalidCreditCardException(creditCard);
		}

		if (purchase.isPickedUp()) {
			throw new TicketsAlreadyPickedUpException();
		}

		purchase.setPickedUp(true);
		purchaseDao.save(purchase);
		return true;
	}

}
