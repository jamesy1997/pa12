import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';

const MovieDetails = () => {

    const movie = useSelector(selectors.getMovies);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        if (!Number.isNaN(id)) {
            dispatch(actions.findMovieById(id));
        }

        return () => dispatch(actions.clearMovie());

    }, [id, dispatch]);

    if (!movie) {
        return null;
    }
        
    return (

        <div>

            <BackLink/>
            
            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{movie.title}</h5>
                    <p className="card-text">{movie.summary}</p>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.duration'/>
                        : <FormattedNumber value={movie.duration}/> min.
                    </p>
                </div>
            </div>

            
        </div>

    );

}

export default MovieDetails;