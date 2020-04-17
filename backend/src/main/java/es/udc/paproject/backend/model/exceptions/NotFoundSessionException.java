package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NotFoundSessionException extends Exception {

	public NotFoundSessionException() {
		super("Session not found");
	}

}
