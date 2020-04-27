package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.rest.common.MillisConversor;

public class PurchaseConversor {

	private PurchaseConversor() {
	}

	public final static List<PurchaseSummaryDto> toPurchaseSummaryDtos(List<Purchase> purchases) {
		return purchases.stream().map(p -> toPurchaseSummaryDto(p)).collect(Collectors.toList());
	}

	public final static PurchaseDto toPurchaseDto(Purchase purchase) {

		return new PurchaseDto(purchase.getId(), purchase.getTicket(), MillisConversor.toMillis(purchase.getDate()),
				purchase.isPickedUp(), purchase.getUser().getUserName(),
				purchase.getSession().getRoom().getCinema().getName());
	}

	private final static PurchaseSummaryDto toPurchaseSummaryDto(Purchase purchase) {

		return new PurchaseSummaryDto(MillisConversor.toMillis(purchase.getDate()),
				purchase.getSession().getMovie().getTitle(), purchase.getTicket(), purchase.getTotalPrice(),
				MillisConversor.toMillis(purchase.getSession().getDate()),
				purchase.getSession().getRoom().getCinema().getName());
	}

}
