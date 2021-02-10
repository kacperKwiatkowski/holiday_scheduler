import React, {Component} from "react";
import Headerbar from "../componenets/headerbar"
import Calendar from "../componenets/calendar"
import Employees from "../componenets/employees"
import Team from "../componenets/team"
import Teams from "../componenets/teams"
import Settings from "../componenets/settings"
import "../styles/style.css"

import {BrowserRouter, Route, Switch} from 'react-router-dom';

export class Home extends Component{

    render() {
        return (
            <div className="homeSiteWrapper">
                <BrowserRouter>
                    <Headerbar />
                        <Switch>
                            <Calendar exact path={'/home'}/>
                            <Employees exact path={'/employees'}/>
                            <Team exact path={'/team'}/>
                            <Teams exact path={'/teams'}/>
                            <Settings exact path={'/settings'}/>
                        </Switch>
                </BrowserRouter>
            </div>
        );
    }
}

export default Home;