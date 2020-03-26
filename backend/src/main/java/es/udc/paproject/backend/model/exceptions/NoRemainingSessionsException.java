package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NoRemainingSessionsException extends Exception {

	public NoRemainingSessionsException() {
		super("There are no more avaliable sessions today");
	}

}
