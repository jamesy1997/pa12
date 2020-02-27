package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Purchase {

	private Long id;
	private Session session;
	private int ticket;
	private int creditCard;
	private LocalDateTime date;
	private boolean pickedUp;

	public Purchase() {
	}

	public Purchase(Long id, Session session, int ticket, int creditCard, LocalDateTime date, boolean pickedUp) {
		super();
		this.id = id;
		this.session = session;
		this.ticket = ticket;
		this.creditCard = creditCard;
		this.date = date;
		this.pickedUp = pickedUp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
