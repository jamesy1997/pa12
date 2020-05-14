import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    movies: null, 
    billboardDate: null
};

const movies = (state = initialState.movies, action) => {

}
const billboardDate = (state = initialState.billboardDate, action) => {

}

const reducer = combineReducers({
    movies,
    billboardDate
});

export default reducer; 