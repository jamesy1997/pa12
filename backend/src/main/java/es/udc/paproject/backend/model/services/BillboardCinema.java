package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.Session;

public class BillboardCinema {

	private Cinema cinema;
	private Block<BillboardItem<Session>> billboard;

	public BillboardCinema(Cinema cinema, Block<BillboardItem<Session>> billboard) {
		this.cinema = cinema;
		this.billboard = billboard;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Block<BillboardItem<Session>> getBillboard() {
		return billboard;
	}

	public void setBillboard(Block<BillboardItem<Session>> billboard) {
		this.billboard = billboard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billboard == null) ? 0 : billboard.hashCode());
		result = prime * result + ((cinema == null) ? 0 : cinema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillboardCinema other = (BillboardCinema) obj;
		if (billboard == null) {
			if (other.billboard != null)
				return false;
		} else if (!billboard.equals(other.billboard))
			return false;
		if (cinema == null) {
			if (other.cinema != null)
				return false;
		} else if (!cinema.equals(other.cinema))
			return false;
		return true;
	}

}
