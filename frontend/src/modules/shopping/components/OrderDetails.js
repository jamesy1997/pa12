import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {BackLink} from '../../common';
import SessionDetails from '../../billboard';

const OrderDetails = () => {

    const {id} = useParams();
    const order = useSelector(selectors.getOrderSearch);
    const dispatch = useDispatch();

    useEffect(() => {

        if (!Number.isNaN(id)) {   
            dispatch(actions.findOrders);
        }

        return () => dispatch(actions.clearOrder());

    }, [id, dispatch]);

    if (!order) {
        return null;
    }

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">
                        <FormattedMessage id='project.global.fields.purchaseOrder'/> {order.id} 
                    </h5>
                    <h6 className="card-subtitle text-muted">
                        <FormattedDate value={new Date(order.PurchaseDate)}/> - <FormattedTime value={new Date(order.PurchaseDate)}/>
                    </h6>
                    <p className="card-text">
                        {order.creditCard} 
                    </p>
                </div>
            </div>

            <SessionDetails session={order.session}/>

        </div>

    );

}

export default OrderDetails;