import {config, appFetch} from './appFetch';

export const getBillboard = (date, onSuccess) => {

    let path = `/billboard/sessions?cinemaId=2`;
    path += date != null ? `&date=${date}`: "";
    appFetch(path, config('GET'), onSuccess)
}

export const findMovieById = (id, onSuccess) => {
    appFetch(`/billboard/movies/${id}`, config('GET'), onSuccess)
}

export const findSessionById = (id, onSuccess) => {
    appFetch(`/billboard/sessions/${id}`, config('GET'), onSuccess)
}

export const getCities = (onSuccess) => {
    appFetch(`/billboard/cities`, config('GET'), onSuccess)
}

export const getCinemas = (cityId, onSuccess) => {
    appFetch(`/billboard/cities/${cityId}/cinemas`, config('GET'), onSuccess)
}