import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useParams} from 'react-router-dom';
import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import users from '../../users';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {BuyTickets} from '../../shopping';
import {BackLink} from '../../common';

const SessionDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const session = useSelector(selectors.getSessions);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        if (!Number.isNaN(id)) {
            dispatch(actions.findSessionById(id));
        }

        return () => dispatch(actions.clearSession());

    }, [id, dispatch]);

    if (!session) {
        return null;
    }
        
    return (

        <div>

            <BackLink/>
            
            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{session.movieTitle}</h5>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.duration'/>
                        : <FormattedNumber value={session.duration}/> min.
                    </p>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>
                        : <FormattedNumber value={session.price}/>€
                    </p>
                    <p className="card-text font-weight-bold">{session.roomName}</p>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.capacityLeft'/>
                        : <FormattedNumber value={session.capacityLeft}/> 
                    </p>
                    <h6 className="card-subtitle text-muted">
                        <FormattedDate value={new Date(session.date)}/> - <FormattedTime value={new Date(session.date)}/>
                    </h6>
                </div>
            </div>

            {loggedIn &&
                <div>
                    <br/>
                    <BuyTickets sessionId={id}/>
                </div>
            }

            
        </div>

    );

}

export default SessionDetails;