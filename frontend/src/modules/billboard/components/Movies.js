import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {MovieLink} from '../../common';
import {SessionLink} from '../../common';


const Movies = ({movies}) => {

    if (!movies){
        return null;
    }

  
    return(<table className="table table-striped table-hover">

            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.title'/>
                    </th>

                    <th scope="col">
                        <FormattedMessage id='project.global.fields.sessions'/>
                    </th>

                </tr>
            </thead>

            <tbody>
                {movies.map(movie => 
                    <tr key={movie.movieId}>
                    <td><MovieLink id = {movie.movieId} name = {movie.movieTitle}/></td>
                    <td><SessionLink id = {movie.sessionsId} name = {movie.sessionDate}/></td>
                </tr>
                )}

            </tbody>

        </table>
    );
}

Movies.propTypes = {
    movies: PropTypes.array.isRequired
};

export default Movies; 