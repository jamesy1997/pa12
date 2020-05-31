import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import {BackLink} from '../../common';

const DeliverCompleted = () => {
    
    return (
        

        <div className="alert alert-success" role="alert">
            <BackLink/>
            <FormattedMessage id="project.shopping.DeliverCompleted.title.DeliverCompleted"/>
        </div>
    );

}

export default DeliverCompleted;