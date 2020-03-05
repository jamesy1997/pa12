package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughTicketsException extends Exception {

	private int tickets;

	public NotEnoughTicketsException(int tickets) {
		super("There's not enough capacity for buying " + tickets + " tickets");
		this.tickets = tickets;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

}
