package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import es.udc.paproject.backend.model.entities.Session;

public class SessionConversor {

	private SessionConversor() {
	}

	public final static SessionDto toSessionDto(Session session) {

		return new SessionDto(session.getId(), session.getMovie().getTitle(), session.getMovie().getDuration(),
				session.getPrice(), toMillis(session.getDate()), session.getRoom().getName(),
				session.getRoom().getCapacity());

	}

	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}

}
