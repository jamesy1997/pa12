package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class BillboardItemDto<T> {

	private String movieTitle;
	private List<T> items;

	public BillboardItemDto() {
	}

	public BillboardItemDto(String movieTitle, List<T> items) {
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

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
