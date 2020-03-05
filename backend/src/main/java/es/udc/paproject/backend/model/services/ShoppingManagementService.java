package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;

public interface ShoppingManagementService {

	Long buyTickets(es.udc.paproject.backend.model.entities.Session session, Integer tickets, Integer creditCard,
			Long userId) throws InstanceNotFoundException, PermissionRoleException, ExpiratedSessionException,
			NotEnoughTicketsException;

}
