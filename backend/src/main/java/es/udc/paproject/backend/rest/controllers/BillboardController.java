package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BillboardItemConversor.toBillboardItemDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCinemaDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.DateNotAllowedException;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieNotFoundException;
import es.udc.paproject.backend.model.exceptions.SessionNotFoundException;
import es.udc.paproject.backend.model.services.BillboardItem;
import es.udc.paproject.backend.model.services.BillboardService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BillboardItemDto;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.SessionDto;

@RestController
@RequestMapping("/billboard")
public class BillboardController {

	private final static String DATE_NOT_ALLOWED_EXCEPTION_CODE = "project.exceptions.DateNotAllowedException";
	private final static String MOVIE_NOT_FOUND_EXCEPTION_CODE = "project.exceptions.MovieNotFoundException";
	private final static String SESSION_NOT_FOUND_EXCEPTION_CODE = "project.exceptions.SessionNotFoundException";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BillboardService billboardService;

	@ExceptionHandler(DateNotAllowedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleDateNotAllowedException(DateNotAllowedException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(DATE_NOT_ALLOWED_EXCEPTION_CODE, null,
				DATE_NOT_ALLOWED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMovieNotFoundException(MovieNotFoundException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(MOVIE_NOT_FOUND_EXCEPTION_CODE, null,
				MOVIE_NOT_FOUND_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(SessionNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleSessionNotFoundException(SessionNotFoundException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(SESSION_NOT_FOUND_EXCEPTION_CODE, null,
				SESSION_NOT_FOUND_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@GetMapping("/cities")
	public List<CityDto> showCitys() {
		return toCityDtos(billboardService.showCities());
	}

	@GetMapping("/cities/{cityId}/cinemas")
	public List<CinemaDto> showCinemas(@PathVariable Long cityId) {
		return toCinemaDtos(billboardService.showCinemas(cityId));
	}

	@GetMapping("/sessions")
	public List<BillboardItemDto<Long>> showBillboard(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam Long cinemaId)
			throws InstanceNotFoundException, DateNotAllowedException {

		if (date.equals(null))
			date = LocalDate.now();
		List<BillboardItem<Session>> billboard = billboardService.findSessions(date, cinemaId);

		return new ArrayList<>((toBillboardItemDtos(billboard)));
	}

	@GetMapping("/movies/{movieId}")
	public MovieDto findMovieDetail(@PathVariable Long movieId) throws MovieNotFoundException {

		return toMovieDto(billboardService.findMovie(movieId));
	}

	@GetMapping("/sessions/{sessionId}")
	public SessionDto findSessionDetail(@PathVariable Long sessionId)
			throws SessionNotFoundException, ExpiratedSessionException {

		return toSessionDto(billboardService.findSessionDetail(sessionId, LocalDateTime.now()));
	}

}
