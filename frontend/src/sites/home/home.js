import React, {Component} from "react";
import Sidebar from "../../componenets/sidebar/sidebar"
import Headerbar from "../../componenets/headerbar/headerbar"
import Calendar from "../../componenets/calendar/calendar"
import Team from "../../componenets/team/team"
import Teams from "../../componenets/teams/teams"
import Settings from "../../componenets/settings/settings"

import {BrowserRouter, Route, Switch} from 'react-router-dom';

export class Home extends Component{

    render() {
    return (
        <div>
            <BrowserRouter>
                <Sidebar />
                <Headerbar />
                
                <Switch>
                    <Calendar exact path={'/home'}/>

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