import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const getBillboardCompleted = movies => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED,
    movies
});

export const getBillboard = date => dispatch => {
    backend.billboardService.getBillboard(date,
        movies => dispatch(getBillboardCompleted(movies)))
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