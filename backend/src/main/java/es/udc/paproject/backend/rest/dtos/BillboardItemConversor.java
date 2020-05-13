package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.services.BillboardItem;

public class BillboardItemConversor {

	private BillboardItemConversor() {
	}

	public final static BillboardItemDto<Long> toBillboardItemDto(BillboardItem<Session> billboardItem) {

		List<Long> items = billboardItem.getItems().stream().map(i -> i.getId()).collect(Collectors.toList());
		List<LocalDateTime> sessionDate = billboardItem.getItems().stream().map(i -> i.getDate())
				.collect(Collectors.toList());

		return new BillboardItemDto<>(billboardItem.getMovie().getId(), billboardItem.getMovie().getTitle(), items,
				sessionDate);
	}

	public final static List<BillboardItemDto<Long>> toBillboardItemDtos(List<BillboardItem<Session>> billboard) {
		return billboard.stream().map(b -> toBillboardItemDto(b)).collect(Collectors.toList());
	}
}
