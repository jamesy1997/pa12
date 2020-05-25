import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import {useHistory} from 'react-router-dom';


import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../../billboard/selectors';



const BuyTickets = () => {

    const session = useSelector(selectors.getSessions);
    const dispatch = useDispatch();
    const [quantity, setQuantity] = useState(1);
    const [backendErrors, setBackendErrors] = useState(null);
    const [creditCard, setCreditCard] = useState('');
    const history = useHistory();
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.buy(session.id, quantity,
                creditCard, 
                () => history.push('/shopping/purchase-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }
    
    return (

        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <div className="card bg-ligth border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.BuyTickets.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="quantity" className="offset-md-5 col-md-1 col-form-label">
                                <FormattedMessage id="project.global.fields.quantity"/>
                            </label>
                            <div className="col-md-2">
                                <input type="number" id="quantity" className="form-control"
                                    value={quantity}
                                    onChange={e => setQuantity(Number(e.target.value))}
                                    autoFocus
                                    min="1" 
                                    max="10"
                                />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="creditCard" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="creditCard" className="form-control"
                                    value={creditCard}
                                    onChange={e => setCreditCard(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-6 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id='project.shopping.buyTickets'/>
                                </button>
                            </div>
                        </div> 
                    </form> 
                </div>
            </div>
        </div>
    );

}

BuyTickets.propTypes = {
    sessionId: PropTypes.number.isRequired
};

export default BuyTickets;