package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class BillboardItemDto<Long> {

	private String movieTitle;
	private List<Long> items;

	public BillboardItemDto() {
	}

	public BillboardItemDto(String movieTitle, List<Long> items) {
		super();
		this.movieTitle = movieTitle;
		this.items = items;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public List<Long> getItems() {
		return items;
	}

	public void setItems(List<Long> items) {
		this.items = items;
	}

}
