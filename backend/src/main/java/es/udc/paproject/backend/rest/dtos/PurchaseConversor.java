package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Purchase;

public class PurchaseConversor {

	private PurchaseConversor() {
	}

	public final static List<PurchaseSummaryDto> toPurchaseSummaryDtos(List<Purchase> purchases) {
		return purchases.stream().map(p -> toPurchaseSummaryDto(p)).collect(Collectors.toList());
	}

	public final static PurchaseDto toPurchaseDto(Purchase purchase) {

		return new PurchaseDto(purchase.getId(), purchase.getTicket(), toMillis(purchase.getDate()),
				purchase.isPickedUp(), purchase.getUser().getUserName());
	}

	private final static PurchaseSummaryDto toPurchaseSummaryDto(Purchase purchase) {

		return new PurchaseSummaryDto(toMillis(purchase.getDate()), purchase.getSession().getMovie().getTitle(),
				purchase.getTicket(), purchase.getTotalPrice(), toMillis(purchase.getSession().getDate()));
	}

	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
}
