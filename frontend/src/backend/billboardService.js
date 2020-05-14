import {config, appFetch} from './appFetch';

export const getBillboard = (date, cinemaId, onSuccess) => {
    appFetch(`/billboard/sessions?date=${date}&cinemaId=${cinemaId}`, config('GET'), onSuccess)
}