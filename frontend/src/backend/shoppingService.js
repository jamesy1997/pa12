import {config, appFetch} from './appFetch';

export const deliverTickets = (purchaseId, creditCard, onSuccess, onErrors) => 
	appFetch(`/shopping/deliverTickets/${purchaseId}`, 
	config('POST', purchaseId, creditCard), onSuccess, onErrors);

export const buyTickets = ( sessionId, ticket, creditCard, onSuccess, 
    onErrors) =>
    appFetch(`/shopping/buyTickets/${sessionId}`, 
        config('POST', {sessionId, ticket, creditCard}), onSuccess, onErrors);

export const findOrders = ({page}, onSuccess) => 
    appFetch(`/shopping/purchases?page=${page}`, config('GET'), onSuccess);

export const findOrder = (orderId, onSuccess) =>
    appFetch(`/shopping/purchases/${orderId}`, config('GET'), onSuccess);    
    