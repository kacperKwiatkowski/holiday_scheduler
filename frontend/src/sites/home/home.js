import React, {Component} from "react";
import Headerbar from "../../componenets/headerbar/headerbar"
import Calendar from "../../componenets/calendar/calendar"
import Employees from "../../componenets/employees/employees"
import Team from "../../componenets/team/team"
import Teams from "../../componenets/teams/teams"
import Settings from "../../componenets/settings/settings"

import {BrowserRouter, Route, Switch} from 'react-router-dom';

export class Home extends Component{

    render() {
    return (
        <div>
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