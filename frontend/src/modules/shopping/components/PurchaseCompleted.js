import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import {BackLink} from '../../common';

const PurchaseCompleted = () => {

    const orderId = useSelector(selectors.getLastOrderId);

    if (!orderId) {
        return null;
    }
    
    return (
        

        <div className="alert alert-success" role="alert">
            <BackLink/>
            <FormattedMessage id="project.shopping.ticketsPurchased"/>:  
            {orderId}
        </div>
    );

}

export default PurchaseCompleted;