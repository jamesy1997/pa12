import {config, appFetch} from './appFetch';

export const getBillboard = (date, onSuccess) => {
    appFetch(`/billboard/sessions?date=${date}&cinemaId=2`, config('GET'), onSuccess)
}

export const findMovieById = (id, onSuccess) => {
    appFetch(`/billboard/movies/${id}`, config('GET'), onSuccess)
}

export const findSessionById = (id, onSuccess) => {
    appFetch(`/billboard/sessions/${id}`, config('GET'), onSuccess)
}