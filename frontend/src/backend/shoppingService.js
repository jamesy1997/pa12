import {config, appFetch} from './appFetch';

export const buyTickets = (sessionId, quantity, creditCard, onSuccess, 
    onErrors) =>
    appFetch(`/buyTickets/${sessionId}`, 
        config('POST', {quantity, creditCard}), onSuccess, onErrors);

/*export const findOrders = ({page}, onSuccess) => 
    appFetch(`/shopping/orders?page=${page}`, config('GET'), onSuccess);*/
    
/*export const findOrder = (orderId, onSuccess) =>
    appFetch(`/purchases/${orderId}`, config('GET'), onSuccess);*/