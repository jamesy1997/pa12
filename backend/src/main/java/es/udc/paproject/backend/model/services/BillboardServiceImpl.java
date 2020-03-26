package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NoRemainingSessionsException;

@Service
@Transactional
public class BillboardServiceImpl implements BillboardService {

	@Autowired
	private SessionDao sessionDao;

	@Override
	public Block<BillboardItem<LocalDateTime>> findSessions(LocalDateTime date)
			throws InstanceNotFoundException, NoRemainingSessionsException {

		LocalDate today = date.toLocalDate();
		LocalTime todaysLimit = LocalTime.of(23, 59);
		LocalDateTime endDate = LocalDateTime.of(today, todaysLimit);

		Slice<Session> sessions = sessionDao.getSessionByDateOrderByMovieTitle(date, endDate);

		List<Session> sessionList = sessions.getContent();

		Block<BillboardItem<LocalDateTime>> billboard = new Block<>(new ArrayList<>(), sessions.hasNext());

		ListIterator<Session> listIterator = sessionList.listIterator();

		if (sessionList.isEmpty()) {

			throw new NoRemainingSessionsException();

		} else {
			Movie currentMovie = null;
			BillboardItem<LocalDateTime> currentBillboardItem = null;

			while (listIterator.hasNext()) {
				Session session1 = listIterator.next();
				if (currentMovie == null) {
					currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
					currentBillboardItem.getItems().add(session1);
					billboard.getItems().add(currentBillboardItem);
					currentMovie = session1.getMovie();

				} else if (currentMovie != session1.getMovie()) {
					currentBillboardItem = new BillboardItem<>((session1.getMovie()), new ArrayList<>());
					currentBillboardItem.getItems().add(session1);
					billboard.getItems().add(currentBillboardItem);
					currentMovie = session1.getMovie();

				} else {
					currentBillboardItem.getItems().add(session1);
				}
			}
		}
		return billboard;
	}

}
