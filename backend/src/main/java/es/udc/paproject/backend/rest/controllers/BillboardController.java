package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BillboardItemConversor.toBillboardItemDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCinemaDtos;
import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.ExpiratedSessionException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;
import es.udc.paproject.backend.model.exceptions.NotFoundMovieException;
import es.udc.paproject.backend.model.exceptions.NotFoundSessionException;
import es.udc.paproject.backend.model.services.BillboardItem;
import es.udc.paproject.backend.model.services.BillboardService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BillboardItemDto;
import es.udc.paproject.backend.rest.dtos.BillboardParamsDto;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.SessionDto;

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

	@GetMapping("/cities/{cityId}/cinemas")
	public List<CinemaDto> showCinemas(@PathVariable Long cityId) {
		return toCinemaDtos(billboardService.showCinemas(cityId));
	}

	@GetMapping("/billboard/sessions")
	public List<BillboardItemDto<Long>> showBillboard(@RequestBody BillboardParamsDto params)
			throws NoRemainingSessionsException, InstanceNotFoundException {

		Cinema cinema = billboardService.findCinema(params.getCinemaId());
		List<BillboardItem<Session>> billboard = billboardService.findSessions(params.getDate(), cinema);

		return new ArrayList<>((toBillboardItemDtos(billboard)));
	}

	@GetMapping("/movies/{movieId}")
	public MovieDto findMovieDetail(@PathVariable Long movieId) throws NotFoundMovieException {

		return toMovieDto(billboardService.findMovie(movieId));
	}

	@GetMapping("/sessions/{sessionId}")
	public SessionDto findSessionDetail(@PathVariable Long sessionId, @PathVariable LocalDateTime localDateTime)
			throws NotFoundSessionException, ExpiratedSessionException {

		return toSessionDto(billboardService.findSession(sessionId, localDateTime));
	}

}
