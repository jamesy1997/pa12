import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import {BackLink} from '../../common';

const Orders = ({orders}) => (

   

    <table className="table table-striped table-hover">
       
  
        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.orderTitle'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.purchaseDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.tickets'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.totalPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.sessionDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.cinemaName'/>
                </th>
            </tr>
        </thead>

        <tbody>
            
            {orders.map(order => 
                <tr key={order.id}>
                    <td>{order.title}</td>
                    <td>
                        <FormattedDate value={new Date(order.purchaseDate)}/> - <FormattedTime value={new Date(order.purchaseDate)}/>
                    </td>
                    <td>{order.ticket}</td>
                    <td>{order.totalPrice}</td>
                    <td>
                        <FormattedDate value={new Date(order.sessionDate)}/> - <FormattedTime value={new Date(order.sessionDate)}/>
                    </td>
                    <td>{order.cinemaName}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Orders.propTypes = {
    orders: PropTypes.array.isRequired
};

export default Orders;

