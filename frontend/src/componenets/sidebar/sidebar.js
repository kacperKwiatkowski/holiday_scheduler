import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Login} from "../../sites/login/login";
import Calendar from "../calendar/calendar";




export const calendar = () => {
    return(
        <Router>
            <Switch>
                <Route path='/' component={Login}/>
                <Route path='/home' component={Calendar}/>
                <Route path='/employees' component={Login}/>
                <Route path='/team' component={Login}/>
                <Route path='/teams' component={Login}/>
            </Switch>
        </Router>
    )
}