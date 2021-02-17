import React, {Component} from "react";
import Headerbar from "../componenets/headerbar"
import Calendar from "./calendar"
import Employees from "./employees"
import Team from "./team"
import Vacations from "./vacations"
import Settings from "./settings"

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
                    <Route path={'/vacations'} exact component={Vacations}/>
                    <Route path={'/settings'} exact component={Settings}/>
                </BrowserRouter>
            </div>
        );
    }
}

export default Home;