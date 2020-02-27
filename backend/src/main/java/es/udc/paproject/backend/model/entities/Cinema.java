package es.udc.paproject.backend.model.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cinema {

	private Long id;
	private String name;
	private City city;

	public Cinema() {
	}

	public Cinema(Long id, String name, City city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
