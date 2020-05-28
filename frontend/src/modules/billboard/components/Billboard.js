import React, {useState} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import * as selectors from '../selectors';
import * as actions from '../actions';
import DateSelector from './DateSelector';
import CitySelector from './CitySelector';
import CinemaSelector from './CinemaSelector';
import Movies from './Movies';



const Billboard = () => {
    const dispatch = useDispatch();
    const billboardDate = useSelector(selectors.getBillboardDate);
    const movies = useSelector(selectors.getMovies);
    const [cityId, setCityId] = useState('');
    const [cinemaId, setCinemaId] = useState('');
  
    
    
    return (
        
        <div>
        <CitySelector id="cityId" className="custom-select my-1 mr-sm-2"
                value={cityId} onChange={e => setCityId(e.target.value)}/>

        <CinemaSelector id="cinemaId" className="custom-select my-1 mr-sm-2"
                value={cinemaId} onChange={e => setCinemaId(e.target.value)}/>                      

        <DateSelector id="billboardDate" className="custom-select my-2 mr-ms-2"
            value={billboardDate}
            onChange={e=> dispatch(actions.getBillboard(e.target.value))}/>
            
        <Movies movies = {movies} />
        
        </div>
    );
};

export default Billboard; 
