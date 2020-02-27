package es.udc.paproject.backend.model.entities;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class City {

	private Long id;
	private String name;
	private Set<Cinema> cinema;

	public City() {
	}

	public City(Long id, String name, Set<Cinema> cinema) {
		super();
		this.id = id;
		this.name = name;
		this.cinema = cinema;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Cinema> getCinema() {
		return cinema;
	}

	public void setCinema(Set<Cinema> cinema) {
		this.cinema = cinema;
	}

}
