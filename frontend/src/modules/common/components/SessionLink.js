import React from 'react';
import PropTypes from 'prop-types';
import {FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';

import {Link} from 'react-router-dom';

const SessionLink = ({id, name}) => {
    
    return (
        <Link to={`/billboard/sessions/${id}`}>
            <FormattedDate value={new Date(name)}/> - <FormattedTime value={new Date(name)}/>
        </Link>
    );

}

SessionLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default SessionLink; 