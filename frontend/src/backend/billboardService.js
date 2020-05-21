import {config, appFetch} from './appFetch';

export const getBillboard = (date,onSuccess) => {
    appFetch(`/billboard/sessions?date=${date}&cinemaId=1`, config('GET'), onSuccess)
}