package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CityConversor.toCinemaDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;
import es.udc.paproject.backend.model.services.BillboardService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;

@RestController
@RequestMapping("/billboard")
public class BillboardController {

	private final static String NO_REMAINING_SESSIONS_EXCEPTION_CODE = "project.exceptions.NoRemainingSessions";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BillboardService billboardService;

	@ExceptionHandler(NoRemainingSessionsException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public ErrorsDto handleNoRemainingSessionsException(NoRemainingSessionsException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NO_REMAINING_SESSIONS_EXCEPTION_CODE, null,
				NO_REMAINING_SESSIONS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@GetMapping("/cities")
	public List<CityDto> showCitys() {
		return toCityDtos(billboardService.showCities());
	}

	@GetMapping("/cinemas")
	public List<CinemaDto> showCinemas(Long cityId) {
		return toCinemaDtos(billboardService.showCinemas(cityId));
	}

}
