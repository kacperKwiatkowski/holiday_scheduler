import { Login } from "./sites/login/login"
import { Home } from "./sites/home/home"

import React from "react";
import {BrowserRouter as Router,  Route, Switch} from 'react-router-dom';

export class App extends React.Component{
    render() {
        return(
            <div>
                <Login/>
            </div>
            )
    }

}
export default App;
