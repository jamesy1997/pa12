package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class ExpiratedSessionException extends Exception {

	private Long id;

	public ExpiratedSessionException(Long id) {
		super("Session with id " + id + " has already expired");
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
