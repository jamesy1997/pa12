import {config, appFetch} from './appFetch';

/*export const deliverTickets = (purchaseId, creditCard, onSuccess) => 
	appFetch(`/shopping/deliverTickets/${purchaseId}`, 
	config('POST', purchaseId, creditCard), onSuccess);*/

export const buyTickets = (userId,sessionId, quantity, creditCard, onSuccess, 
    onErrors) =>
    appFetch(`/shopping/buyTickets/${sessionId}/buy`, 
        config('POST', {userId,quantity,creditCard}), onSuccess, onErrors);

export const findOrders = ({page}, onSuccess) => 
    appFetch(`/shopping/purchases?page=${page}`, config('GET'), onSuccess);

    export const findOrder = (orderId, onSuccess) =>
    appFetch(`/shopping/purchases/${orderId}`, config('GET'), onSuccess);    
    
/*export const findOrder = (orderId, onSuccess) =>
    appFetch(`/purchases/${orderId}`, config('GET'), onSuccess);*/