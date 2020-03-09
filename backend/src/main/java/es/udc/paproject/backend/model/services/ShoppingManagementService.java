package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidCreditCardException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;
import es.udc.paproject.backend.model.exceptions.TicketsAlreadyPickedUpException;

public interface ShoppingManagementService {

	Purchase buyTickets(Session session, Integer tickets, Integer creditCard, Long userId)
			throws InstanceNotFoundException, PermissionRoleException, ExpiratedSessionException,
			NotEnoughTicketsException;

	Block<Purchase> showPurchases(Long userId, int page, int size)
			throws InstanceNotFoundException, PermissionRoleException;

	boolean deliverTickets(Long userId, Long purchaseId, Integer creditCard)
			throws InstanceNotFoundException, PermissionRoleException, ExpiratedSessionException,
			InvalidCreditCardException, TicketsAlreadyPickedUpException;
}
