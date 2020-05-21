import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';



const Movies = ({movies}) => {

    if (!movies){
        return null;
    }
    
    return(<table className="table table-striped table-hover">

            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.name'/>
                    </th>
                </tr>
            </thead>

            <tbody>
                {movies.map(movie => 
                    <tr key={movie.movieId}>
                    <p className="card-text">
                        {movie.movieTitle}
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