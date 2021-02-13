import React, {Component} from "react";
import Headerbar from "../componenets/headerbar"
import Calendar from "../componenets/calendar"
import Employees from "../componenets/employees"
import Team from "../componenets/team"
import Teams from "../componenets/teams"
import Settings from "../componenets/settings"

import {BrowserRouter, Route} from 'react-router-dom';

export class Home extends Component{

    render() {
        return (
            <div className="homeSiteWrapper">
                <BrowserRouter>
                    <Headerbar />
                    <Route path={'/home'} exact component={Calendar}/>
                    <Route path={'/employees'} exact component={Employees}/>
                    <Route path={'/team'} exact component={Team}/>
                    <Route path={'/teams'} exact component={Teams}/>
                    <Route path={'/settings'} exact component={Settings}/>
                </BrowserRouter>
            </div>
        );
    }
}

export default Home;