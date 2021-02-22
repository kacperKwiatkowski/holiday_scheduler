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
                    <Route exact path={`/home`} component={Calendar}/>
                    <Route exact path={`/employees`} component={Employees} />
                    <Route exact path={`/team`} component={Team}/>
                    <Route exact path={`/vacations`} component={Vacations}/>
                    <Route exact path={`/settings`} component={Settings}/>
                </Switch>
            </Router>


        </div>
    );

}

export default Home;