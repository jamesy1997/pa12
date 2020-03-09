package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class TicketsAlreadyPickedUpException extends Exception {

	public TicketsAlreadyPickedUpException() {
		super("Tickets have already been picked up.");
	}

}
