import backend from '../../backend';
import * as actionTypes from './actionTypes';

const buyCompleted = (orderId) => ({
    type: actionTypes.BUY_COMPLETED,
    orderId
});

export const buy = (sessionId, ticket, creditCard,
    onSuccess, onErrors) => dispatch =>
    backend.shoppingService.buyTickets(sessionId, ticket, creditCard,
        ({id}) => {
            dispatch(buyCompleted(id));
            onSuccess();
        },
        onErrors
);

const deliverCompleted = (purchaseId) => ({
    type: actionTypes.DELIVER_TICKETS_COMPLETED,
    purchaseId
});

export const deliver = (purchaseId, creditCard, 
    onSuccess, onErrors) => dispatch => 
    backend.shoppingService.deliverTickets(purchaseId, creditCard,
        ({id}) => {
            dispatch(deliverCompleted(id));
            onSuccess();
        },
        onErrors
);

const findOrdersCompleted = orderSearch => ({
    type: actionTypes.FIND_ORDERS_COMPLETED,
    orderSearch
});

const clearOrderSearch = () => ({
    type: actionTypes.CLEAR_ORDER_SEARCH
});

const findOrderCompleted = order => ({
    type: actionTypes.FIND_ORDER_COMPLETED,
    order
});

export const clearOrder = () => ({
    type: actionTypes.CLEAR_ORDER
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

export const findOrder = orderId => dispatch => {
    backend.shoppingService.findOrder(orderId, order => {
        dispatch(findOrderCompleted(order));
    });
}    

       
