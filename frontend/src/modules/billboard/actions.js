import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const getBillboardCompleted = (movies, billboardDate) => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED,
    movies,
    billboardDate
});

export const getBillboard = billboardDate => dispatch => {
    backend.billboardService.getBillboard(billboardDate,
        movies => dispatch(getBillboardCompleted(movies,billboardDate)))
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


