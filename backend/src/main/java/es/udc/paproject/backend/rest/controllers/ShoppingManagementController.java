package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.PurchaseConversor.toPurchaseDto;
import static es.udc.paproject.backend.rest.dtos.PurchaseConversor.toPurchaseSummaryDtos;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Purchase;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidCreditCardException;
import es.udc.paproject.backend.model.exceptions.NotEnoughTicketsException;
import es.udc.paproject.backend.model.exceptions.TicketsAlreadyPickedUpException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingManagementService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.BuyTicketsParamsDto;
import es.udc.paproject.backend.rest.dtos.DeliverTicketsParamsDto;
import es.udc.paproject.backend.rest.dtos.IdDto;
import es.udc.paproject.backend.rest.dtos.PurchaseDto;
import es.udc.paproject.backend.rest.dtos.PurchaseSummaryDto;

@RestController
@RequestMapping("/shopping")
public class ShoppingManagementController {

	private final static String EXPIRATED_SESSION_EXCEPTION_CODE = "project.exceptions.ExpiratedSessionException";
	private final static String NOT_ENOUGH_TICKETS_EXCEPTION_CODE = "project.exceptions.NotEnoughTicketsException";
	private final static String INVALID_CREDIT_CARD_EXCEPTION_CODE = "project.exceptions.InvalidCreditCardException";
	private final static String TICKETS_ALREADY_PICKEDUP_EXCEPTION_CODE = "project.exceptions.TicketsAlreadyPickedUpException";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ShoppingManagementService shoppingManagementService;

	@ExceptionHandler(ExpiratedSessionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleExpiratedSessionException(ExpiratedSessionException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(EXPIRATED_SESSION_EXCEPTION_CODE,
				new Object[] { exception.getId() }, EXPIRATED_SESSION_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(NotEnoughTicketsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleNotEnoughTicketsException(NotEnoughTicketsException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_ENOUGH_TICKETS_EXCEPTION_CODE,
				new Object[] { exception.getTickets() }, NOT_ENOUGH_TICKETS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(TicketsAlreadyPickedUpException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleTicketsAlreadyPickedUpException(TicketsAlreadyPickedUpException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(TICKETS_ALREADY_PICKEDUP_EXCEPTION_CODE, null,
				TICKETS_ALREADY_PICKEDUP_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(InvalidCreditCardException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleInvalidCreditCardException(InvalidCreditCardException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(INVALID_CREDIT_CARD_EXCEPTION_CODE,
				new Object[] { exception.getCreditCard() }, INVALID_CREDIT_CARD_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@PostMapping("/buyTickets/{sessionId}")
	public IdDto buyTickets(@RequestAttribute Long userId, @PathVariable Long sessionId,
			@RequestBody BuyTicketsParamsDto params)
			throws InstanceNotFoundException, ExpiratedSessionException, NotEnoughTicketsException {

		return new IdDto(shoppingManagementService
				.buyTickets(sessionId, params.getTicket(), params.getCreditCard(), userId).getId());
	}

	@GetMapping("/purchases")
	public BlockDto<PurchaseSummaryDto> showPurchases(@RequestAttribute Long userId,
			@RequestParam(defaultValue = "0") int page) throws InstanceNotFoundException {

		Block<Purchase> purchaseBlock = shoppingManagementService.showPurchases(userId, page, 10);

		return new BlockDto<>(toPurchaseSummaryDtos(purchaseBlock.getItems()), purchaseBlock.getExistMoreItems());
	}

	@PostMapping("/deliverTickets/{purchaseId}")
	public PurchaseDto deliverTickets(@PathVariable Long purchaseId, @RequestBody DeliverTicketsParamsDto params)
			throws InstanceNotFoundException, ExpiratedSessionException, InvalidCreditCardException,
			TicketsAlreadyPickedUpException {

		return toPurchaseDto(shoppingManagementService.deliverTickets(purchaseId, params.getCreditCard()));

	}
}
