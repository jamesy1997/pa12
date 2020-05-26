import {config, appFetch} from './appFetch';

export const getBillboard = (date,onSuccess) => {
    appFetch(`/billboard/sessions?date=${date}&cinemaId=3`, config('GET'), onSuccess)
}

export const findMovieById = (id, onSuccess) => {
    appFetch(`/billboard/movies/${id}`, config('GET'), onSuccess)
}

export const findSessionById = (id, onSuccess) => {
    appFetch(`/billboard/sessions/${id}`, config('GET'), onSuccess)
}

export const findCities = (onSuccess) => {
    appFetch('/billboard/cities', config('GET'), onSuccess)
}

export const getCinemas = (onSucess) => {
    appFetch('billboard/cities/1/cinemas', config('GET'), onSucess)
}