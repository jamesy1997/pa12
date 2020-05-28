import * as actionTypes from './actionTypes';
import backend from '../../backend';
import * as selectors from './selectors';

export const getBillboardCompleted = (movies, billboardDate, sessions) => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED,
    movies,
    billboardDate,
    sessions
});

export const getBillboard = (billboardDate, sessions) => dispatch => {
    backend.billboardService.getBillboard(billboardDate,
        movies => dispatch(getBillboardCompleted(movies,billboardDate, sessions)))
};

export const findMovieByIdCompleted = movies => ({
    type: actionTypes.GET_MOVIE_BY_ID_COMPLETED,
    movies
})

export const findMovieById = id => dispatch => {
    backend.billboardService.findMovieById(id, 
        movies => dispatch(findMovieByIdCompleted(movies)))
};

export const clearMovie = () => ({
    type: actionTypes.CLEAR_MOVIE
});

export const findSessionByIdCompleted = sessions => ({
    type: actionTypes.GET_SESSION_BY_ID_COMPLETED,
    sessions
})

export const findSessionById = id => dispatch => {
    backend.billboardService.findSessionById(id, 
        sessions => dispatch(findSessionByIdCompleted(sessions)))
};

export const clearSession = () => ({
    type: actionTypes.CLEAR_SESSION
});


export const findCitiesCompleted = cities => ({
    type: actionTypes.GET_CITIES_COMPLETED, 
    cities
});

export const findCities = () => (dispatch, getState) => {

    const cities = selectors.getCities(getState());

    if (!cities) {

        backend.billboardService.getCities(
            cities => dispatch(findCitiesCompleted(cities))
        );
    }

}

export const findCinemasCompleted = cinemas => ({
    type: actionTypes.GET_CINEMAS_COMPLETED, 
    cinemas
});

export const findCinemas = () => (dispatch, getState) => {

    const cinemas = selectors.getCinemas(getState());

    if (!cinemas) {

        backend.billboardService.getCinemas(
            cinemas => dispatch(findCinemasCompleted(cinemas))
        );
    }

}