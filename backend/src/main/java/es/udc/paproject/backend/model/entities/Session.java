package es.udc.paproject.backend.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
@org.hibernate.annotations.BatchSize(size = 10)
public class Session {

	private Long id;
	private Movie movie;
	private Room room;
	private LocalDateTime date;
	private BigDecimal price;
	private Integer ticketsPurchased;
	private long version;

	public Session() {
	}

	public Session(Movie movie, Room room, LocalDateTime date, BigDecimal price, Integer ticketsPurchased) {

		this.movie = movie;
		this.room = room;
		this.date = date;
		this.price = price;
		this.ticketsPurchased = ticketsPurchased;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "roomId")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getTicketsPurchased() {
		return ticketsPurchased;
	}

	public void setTicketsPurchased(Integer ticketsPurchased) {
		this.ticketsPurchased = ticketsPurchased;
	}

	@Version
	public long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;

	}

}
