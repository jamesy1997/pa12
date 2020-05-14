import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const getBillboardCompleted = (movies, billboardDate) => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED
});

export const getBillboard = billboardDate => dispatch => {
    backend.billboardService.getBillboard(billboardDate,
        result => dispatch(getBillboardCompleted(billboardDate, result)))
};