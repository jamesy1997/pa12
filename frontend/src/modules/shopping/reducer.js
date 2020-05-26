import {combineReducers} from 'redux';

//import users from '../users';
import * as actionTypes from './actionTypes';

const initialState = {
    //session: null,
    lastOrderId: null,
    orderSearch: null
};

/*const session = (state = initialState.session, action) => {
    switch (action.type) {

        case users.actionTypes.LOGIN_COMPLETED:
            return action.authenticatedUser.session;

        case users.actionTypes.SIGN_UP_COMPLETED:
            return action.authenticatedUser.session;

        case actionTypes.BUY_COMPLETED:
            return {id: state.id, totalPrice: 0, totalQuantity: 0};

        default:
            return state;

    }
}*/
const lastOrderId = (state = initialState.lastOrderId, action) => {

    switch (action.type) {

        case actionTypes.BUY_COMPLETED:
            return action.orderId;

        default:
            return state;

    }

}

const orderSearch = (state = initialState.orderSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_ORDERS_COMPLETED:
            return action.orderSearch;

        case actionTypes.CLEAR_ORDER_SEARCH:
            return initialState.orderSearch;

        default:
            return state;

    }

}

const reducer = combineReducers({
    //session,
    lastOrderId,
    orderSearch

});

export default reducer;
