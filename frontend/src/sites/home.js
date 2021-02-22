import React from "react";
import Headerbar from "../componenets/headerbar"
import Calendar from "./calendar"
import Employees from "./employees"
import Team from "./team"
import Vacations from "./vacations"
import Settings from "./settings"

import {BrowserRouter as Router, Switch, Route } from 'react-router-dom';

const Home = () => {

    return (
        <div className="homeSiteWrapper">
            <Router>
                <Headerbar />
                <Switch>
                    <Route path={`/home`} component={Calendar}/>
                    <Route path={`/employees`} component={Employees} />
                    <Route path={`/team`} component={Team}/>
                    <Route path={`/vacations`} component={Vacations}/>
                    <Route path={`/settings`} component={Settings}/>
                </Switch>
            </Router>


        </div>
    );

}

export default Home;