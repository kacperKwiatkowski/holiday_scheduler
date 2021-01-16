
import Welcome from "./sites/welcome/welcome"
import Home from "./sites/home/home"

import React, {Component} from "react";
import {BrowserRouter, Route, Switch} from 'react-router-dom';

export class App extends Component{

    constructor(){
        super();

        this.state = {
            loggedInStatus: "NOT_LOGGED_IN",
            user: {}
        };

        this.handleLogin = this.handleLogin.bind(this);
    }

    handleLogin(data) {
        this.setState({
            loggedInStatus: "LOGGED_IN",
            "user": data
        })
    }

    render() {
        return(
            <BrowserRouter>
                <Switch>
                    <Route exact path={'/'}
                        render={props =>(
                        <Welcome { ... props} handleLogin={this.handleLogin} loggedInStatus={this.state.loggedInStatus}/>) }
                    />
                    <Route exact path={'/home'}
                        render={props => (
                            <Home { ... props} loggedInStatus={this.state.loggedInStatus}/>) }  
                    />
                </Switch>
            </BrowserRouter>
        )
    }

}
export default App;
