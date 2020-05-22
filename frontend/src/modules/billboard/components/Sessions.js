import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';



const Sessions = ({sessions}) => {

    if (!sessions){
        return null;
    }
    
    return(<table className="table table-striped table-hover">

            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.title'/>
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.movieDetail'/>
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.sessions'/>
                    </th>
                </tr>
            </thead>

            <tbody>
                {sessions.map(session => 
                    <tr key={session.id}>
                    <p className="card-text">
                        {session.id}
                    </p>
                    <td>{sessions.date}</td>
                </tr>
                )}

            </tbody>

        </table>
    );
}

Sessions.propTypes = {
    sessions: PropTypes.array.isRequired
};

export default Sessions; 