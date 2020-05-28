import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    movies: null,
    billboardDate: null,
    sessions: null, 
    cities: null,
    cinemas:null
};

const sessions = (state = initialState.sessions, action) => {
    switch (action.type) {

        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.billboardDate;
            
        case actionTypes.GET_SESSION_BY_ID_COMPLETED:
            return action.sessions;
        
        case actionTypes.CLEAR_SESSION:
            return initialState.sessions;

        default:
            return state;    

    }
}

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

const cities = (state = initialState.cities, action) => {
    
    switch(action.type) {

        case actionTypes.GET_CITIES_COMPLETED:
            return action.cities;

        default:
            return state;
    }
}

const cinemas = (state = initialState.cinemas, action) => {
    
    switch(action.type) {

        case actionTypes.GET_CINEMAS_COMPLETED:
            return action.cinemas;

        default:
            return state;
    }
}


const reducer = combineReducers({
    movies,
    billboardDate,
    sessions,
    cities,
    cinemas
});

export default reducer; 