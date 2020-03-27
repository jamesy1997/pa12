package es.udc.paproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	private Long id;
	private String title;
	private String summary;
	private Integer duration;

	public Movie() {
	}

	public Movie(String title, String summary, Integer duration) {

		this.title = title;
		this.summary = summary;
		this.duration = duration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
//		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
//		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
//		Movie other = (Movie) obj;
//		if (duration == null) {
//			if (other.duration != null)
//				return false;
//		} else if (!duration.equals(other.duration))
//			return false;
//		if (summary == null) {
//			if (other.summary != null)
//				return false;
//		} else if (!summary.equals(other.summary))
//			return false;
//		if (title == null) {
//			if (other.title != null)
//				return false;
//		} else if (!title.equals(other.title))
//			return false;
//		return true;
//	}

}
