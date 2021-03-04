import Welcome from "./sites/welcome"
import React from "react";
import {Router, Route} from 'react-router-dom';
import { Provider } from "react-redux";
import store from "./store/store";
import Home from "./sites/home"
import Employees from "./sites/employees"
import Team from "./sites/team"
import Teams from "./sites/teams"
import Vacations from "./sites/vacations"
import Settings from "./sites/settings"
import Footer from "./componenets/footer"
import history from "./componenets/history";

const App = () => {
    return(
        <Provider store={store}>
            <Router history={history}>
                <Route exact path={'/'} component={Welcome}/>
                <Route path={`/home`} component={Home}/>
                <Route path={`/employees`} component={Employees} />
                <Route path={`/team`} component={Team}/>
                <Route path={`/teams`} component={Teams}/>
                <Route path={`/vacations`} component={Vacations}/>
                <Route path={`/settings`} component={Settings}/>
            </Router>
            <Footer />
        </Provider>
    )
}
export default App;
