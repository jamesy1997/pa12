package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.Purchase;

public class PurchaseHistoryItem {

	private Cinema cinema;
	private Purchase purchase;

	public PurchaseHistoryItem(Purchase purchase, Cinema cinema) {

		this.cinema = cinema;
		this.purchase = purchase;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinema == null) ? 0 : cinema.hashCode());
		result = prime * result + ((purchase == null) ? 0 : purchase.hashCode());
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
		PurchaseHistoryItem other = (PurchaseHistoryItem) obj;
		if (cinema == null) {
			if (other.cinema != null)
				return false;
		} else if (!cinema.equals(other.cinema))
			return false;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PurchaseHistoryItem [cinema=" + cinema + ", purchase=" + purchase + "]";
	}

}
