import React from "react";

import { Home } from '../home/home'

import "./login.css"

export class Login extends React.Component {

    render() {
        return(
            <div className="loginSiteWrapper">
{/*                <form className="loginFormWrapper">
                    <input className="loginFormInput" type="text" name="email" placeholder="email" />

                    <input className="loginFormInput" type="password" name="password" placeholder="password" />
                </form>*/}
                <button className="loginFormButton" type="submit" value="Log in"
                        onClick={() => <Home />}/>
            </div>
        )
    }

}

export default Login;