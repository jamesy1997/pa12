package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidCreditCardException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.TicketsAlreadyPickedUpException;

public interface ShoppingManagementService {

	Purchase buyTickets(Long sessionId, Integer tickets, Integer creditCard, Long userId)
			throws InstanceNotFoundException, ExpiratedSessionException, NotEnoughTicketsException;

	Block<Purchase> showPurchases(Long userId, int page, int size) throws InstanceNotFoundException;

	Purchase deliverTickets(Long purchaseId, Integer creditCard) throws InstanceNotFoundException,
			ExpiratedSessionException, InvalidCreditCardException, TicketsAlreadyPickedUpException;

}
