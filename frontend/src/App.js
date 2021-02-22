import Welcome from "./sites/welcome"
import Home from "./sites/home"
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { Provider } from "react-redux";
import store from "./store/store";

 const App = () => {

    return(
        <Provider store={store}>
            <Router>
                <Switch>
                    <Route exact path={'/'} component={Welcome}/>
                    <Route exact path={'/home'} component={Home}/>
                </Switch>
            </Router>
        </Provider>
    )

}
export default App;
