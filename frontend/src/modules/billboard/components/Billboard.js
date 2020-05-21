import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import * as selectors from '../selectors';
import * as actions from '../actions';
import DateSelector from './DateSelector';
import Movies from './Movies';


const Billboard = () => {
    const dispatch = useDispatch();
    const billboardDate = useSelector(selectors.getBillboardDate);
    const movies = useSelector(selectors.getMovies);
    
    /*<DateSelector id="billboardDate" className="custom-select my-2 mr-ms-2"
            value={billboardDate}
            onChange={e=> dispatch(actions.getBillboard(e.target.value))}
            /> */
    return (
        
        <div>
        <Movies movies = {movies} />
        </div>
    );
};

export default Billboard; 
