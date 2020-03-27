package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
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
	public Purchase buyTickets(Long sessionId, Integer tickets, Integer creditCard, Long userId)
			throws InstanceNotFoundException, ExpiratedSessionException, NotEnoughTicketsException {

		User user = permissionChecker.checkUser(userId);
		Optional<Session> optSession = sessionDao.findById(sessionId);

		if (!optSession.isPresent()) {
			throw new InstanceNotFoundException("session", sessionId);
		}

		Session session = optSession.get();

		if (session.getDate().isBefore(LocalDateTime.now())) {
			throw new ExpiratedSessionException(session.getId());
		}

		if ((tickets > session.getRoom().getCapacity())) {
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
		Slice<Purchase> slice = purchaseDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));

		return new Block<>(slice.getContent(), slice.hasNext());

	}

	@Override
	public Purchase deliverTickets(Long userId, Long purchaseId, Integer creditCard) throws InstanceNotFoundException,
			ExpiratedSessionException, InvalidCreditCardException, TicketsAlreadyPickedUpException {

		permissionChecker.checkUser(userId);
		Optional<Purchase> optPurchase;
		Purchase purchase;
		optPurchase = purchaseDao.findById(purchaseId);

		try {
			purchase = optPurchase.get();
		} catch (NoSuchElementException e) {
			throw new InstanceNotFoundException("purchase", purchaseId);
		}

		if (purchase.getSession().getDate().isBefore(LocalDateTime.now())) {
			throw new ExpiratedSessionException(purchase.getSession().getId());
		}

		if (!creditCard.equals(purchase.getCreditCard())) {
			throw new InvalidCreditCardException(creditCard);
		}

		if (purchase.isPickedUp()) {
			throw new TicketsAlreadyPickedUpException();
		}

		purchase.setPickedUp(true);
		purchaseDao.save(purchase);
		return purchase;
	}

	@Override
	public Block<PurchaseHistoryItem> showPurchaseHistory(Long userId, int page, int size)
			throws InstanceNotFoundException {

		permissionChecker.checkUser(userId);
		Slice<Purchase> purchases = purchaseDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));

		List<Purchase> purchaseList = purchases.getContent();

		Block<PurchaseHistoryItem> purchaseHistory = new Block<>(new ArrayList<>(), purchases.hasNext());

		ListIterator<Purchase> listIterator = purchaseList.listIterator();

		while (listIterator.hasNext()) {

			Purchase purchase1 = listIterator.next();
			Cinema cinema1 = purchase1.getSession().getRoom().getCinema();

			PurchaseHistoryItem purchaseHistoryItem = new PurchaseHistoryItem(purchase1, cinema1);
			purchaseHistory.getItems().add(purchaseHistoryItem);

		}

		return purchaseHistory;
	}

}
