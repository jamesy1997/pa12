package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception {

	public MovieNotFoundException() {
		super("Movie not found");
	}

}