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

const findOrdersCompleted = orderSearch => ({
    type: actionTypes.FIND_ORDERS_COMPLETED,
    orderSearch
});

const clearOrderSearch = () => ({
    type: actionTypes.CLEAR_ORDER_SEARCH
});

export const findOrders = criteria => dispatch => {

    dispatch(clearOrderSearch());
    backend.shoppingService.findOrders(criteria, 
        result => dispatch(findOrdersCompleted({criteria, result})));

}

export const previousFindOrdersResultPage = criteria => 
    findOrders({page: criteria.page-1});

export const nextFindOrdersResultPage = criteria => 
    findOrders({page: criteria.page+1});


       
