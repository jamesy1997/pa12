package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class DateNotAllowedException extends Exception {

	public DateNotAllowedException() {
		super("Date not allowed :(");
	}
}
