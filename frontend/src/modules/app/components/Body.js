import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {MovieDetails} from '../../billboard';
import {SessionDetails} from '../../billboard';
import {PurchaseCompleted, FindOrders, FindOrdersResult, OrderDetails, BuyTickets, DeliverTickets} from '../../shopping';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/billboard/movies/:id"><MovieDetails/></Route>
                <Route exact path="/billboard/sessions/:id"><SessionDetails/></Route>
                {loggedIn && <Route exact path="/shopping/buy"><BuyTickets/></Route>}
                {loggedIn && <Route exact path="/shopping/purchase-completed"><PurchaseCompleted/></Route>}
                {loggedIn && <Route exact path="/shopping/find-orders"><FindOrders/></Route>}
                {loggedIn && <Route exact path="/shopping/find-orders-result"><FindOrdersResult/></Route>}
                {loggedIn && <Route exact path="/shopping/deliver"><DeliverTickets/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
