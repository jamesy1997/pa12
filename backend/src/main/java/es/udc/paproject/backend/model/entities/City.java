package es.udc.paproject.backend.model.entities;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class City {

	private Long id;
	private String name;
	private Set<Cinema> cinemas;

	public City() {
	}

	public City(String name) {

		this.name = name;
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

	@OneToMany(mappedBy = "city")
	public Set<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(Set<Cinema> cinema) {
		this.cinemas = cinema;
	}

	@Transient
	public Optional<Cinema> getCinema(Long cinemaId) {

		return cinemas.stream().filter(cinema -> cinema.getId().equals(cinemaId)).findFirst();
	}

	public void addCinema(Cinema cinema) {
		cinemas.add(cinema);
		cinema.setCity(this);
	}

}
