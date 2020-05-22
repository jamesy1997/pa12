import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    movies: null,
    billboardDate: null
};

const movies = (state = initialState.movies, action) => {

    switch (action.type) {

        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.movies;

        case actionTypes.GET_MOVIE_BY_ID_COMPLETED:
            return action.movies;
        
        case actionTypes.CLEAR_MOVIE: 
            return initialState.movies;

        default:
            return state;

    }

}

const billboardDate = (state = initialState.billboardDate, action) => {
    switch (action.type) {

        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.billboardDate;

        default:
            return state;

    }

}


const reducer = combineReducers({
    movies,
    billboardDate
    
});

export default reducer; 