import {combineReducers} from 'redux';

import users from '../users';
import * as actionTypes from './actionTypes';

const initialState = {
    session: null
}

const session = (state = initialState.session, action) => {
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
}

const reducer = combineReducers({
    session

});

export default reducer;