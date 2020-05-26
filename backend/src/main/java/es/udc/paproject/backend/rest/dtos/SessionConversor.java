
package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.rest.common.MillisConversor;

public class SessionConversor {

	private SessionConversor() {
	}

	public final static SessionDto toSessionDto(Session session) {

		return new SessionDto(session.getMovie().getTitle(), session.getMovie().getDuration(), session.getPrice(),
				MillisConversor.toMillis(session.getDate()), session.getRoom().getName(),
				(session.getRoom().getCapacity() - session.getTicketsPurchased()),
				session.getRoom().getCinema().getName());

	}

}
