package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class BillboardItemDto<Long> {

	private Long movieId;
	private String movieTitle;
	private List<Long> sessionsId;
	private List<LocalDateTime> sessionDate;

	public BillboardItemDto() {
	}

	public BillboardItemDto(Long movieId, String movieTitle, List<Long> sessionsId, List<LocalDateTime> sessionDate) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.sessionsId = sessionsId;
		this.sessionDate = sessionDate;

	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public List<Long> getSessionsId() {
		return sessionsId;
	}

	public void setSessionsId(List<Long> sessionsId) {
		this.sessionsId = sessionsId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public List<LocalDateTime> getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(List<LocalDateTime> sessionDate) {
		this.sessionDate = sessionDate;
	}

}
