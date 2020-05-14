import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import billboard from '../modules/billboard';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    billboard: billboard.reducer
});

export default rootReducer;
