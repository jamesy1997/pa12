import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useParams} from 'react-router-dom';
import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import users from '../../users';

import {BackLink} from '../../common';
import DeliverTickets from './DeliverTickets';

const DeliverTicketsResult = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const dispatch = useDispatch();
    const {id} = useParams();
        
    return (

        <div>

            <BackLink/>

            {loggedIn &&
                <div>
                    <br/>
                    <DeliverTickets/>
                </div>
            }

            
        </div>

    );

}

export default DeliverTicketsResult;