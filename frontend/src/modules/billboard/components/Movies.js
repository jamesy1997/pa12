import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {MovieLink} from '../../common';



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
                    <p className="card-text">
                        {movie.sessionDate}
                    </p>
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