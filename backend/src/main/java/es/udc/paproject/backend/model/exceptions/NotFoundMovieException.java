package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NotFoundMovieException extends Exception {

	public NotFoundMovieException() {
		super("Movie not found");
	}

}