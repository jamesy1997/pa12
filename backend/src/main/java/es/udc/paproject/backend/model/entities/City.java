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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((cinema == null) ? 0 : cinema.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		City other = (City) obj;
//		if (cinema == null) {
//			if (other.cinema != null)
//				return false;
//		} else if (!cinema.equals(other.cinema))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}

}
