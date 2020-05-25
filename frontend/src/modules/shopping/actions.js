import backend from '../../backend';
import * as actionTypes from './actionTypes';

const buyCompleted = (orderId) => ({
    type: actionTypes.BUY_COMPLETED,
    orderId
});

export const buy = (sessionId, quantity, creditCard,
    onSuccess, onErrors) => dispatch =>
    backend.shoppingService.buyTickets(sessionId, quantity, creditCard,
        ({id}) => {
            dispatch(buyCompleted(id));
            onSuccess();
        },
        onErrors);

       
