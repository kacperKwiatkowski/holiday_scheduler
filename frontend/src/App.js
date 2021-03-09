import Welcome from "./sites/welcome"
import React, {useState, useEffect} from "react";
import {Router, Route, Switch, Redirect} from 'react-router-dom';
import { Provider } from "react-redux";
import store from "./store/store";
import Headerbar from "./componenets/headerbar"
import Home from "./sites/home"
import Employees from "./sites/employees"
import Team from "./sites/team"
import Teams from "./sites/teams"
import Vacations from "./sites/vacations"
import Settings from "./sites/settings"
import Footer from "./componenets/footer"
import history from "./componenets/history";

const App = () => {

    const LoginContainer = () => (
        <div className="container">
          <Route exact path="/" render={() => <Redirect to="/" />} />
          <Route path="/" component={Welcome} />
        </div>
      )
      
      const DefaultContainer = () => (
            <div>
                { history.location.pathname === "/" ? null : <Headerbar /> }
                        <Route path={`/home`} component={Home}/>
                        <Route path={`/employees`} component={Employees} />
                        <Route path={`/team`} component={Team}/>
                        <Route path={`/teams`} component={Teams}/>
                        <Route path={`/vacations`} component={Vacations}/>
                        <Route path={`/settings`} component={Settings}/>
                { history.location.pathname === "/" ? null : <Footer />}
            </div>
        )

      
    return(
        <Provider store={store}>

            <Router history={history}>
                <Switch>
                    <Route exact path="/" component={LoginContainer}/>
                    <Route component={DefaultContainer}/>

                </Switch>            
            </Router>



        </Provider>
    
    )


    // console.log(history.location.pathname)
    // return(
    //     <Provider store={store}>

    //         <Router history={history}>

    //         { history.location.pathname === "/" ? null : <Headerbar /> }
            
    //             <Route exact path={'/'} component={Welcome}/>
    //             <Switch>

    //                 <Route path={`/home`} component={Home}/>
    //                 <Route path={`/employees`} component={Employees} />
    //                 <Route path={`/team`} component={Team}/>
    //                 <Route path={`/teams`} component={Teams}/>
    //                 <Route path={`/vacations`} component={Vacations}/>
    //                 <Route path={`/settings`} component={Settings}/>
    //             </Switch>


    //         { history.location.pathname === "/" ? null : <Footer />}
    //         </Router>



    //     </Provider>
    // )
}
export default App;
