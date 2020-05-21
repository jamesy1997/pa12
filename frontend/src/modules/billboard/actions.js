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