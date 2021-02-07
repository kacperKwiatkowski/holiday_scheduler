import React, {Component} from "react";
import  { Redirect } from 'react-router-dom'
import Axios from "axios";

import "../../styles/style.css"
import "../../App.css"

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    initialState = {username: '', password: ''}
    
    handleChange = event => {
    this.setState({
    [event.target.name]: event.target.value 
    })
    }
    
    handleSubmit = (event) => {

    event.preventDefault()

    Axios.post("http://localhost:8080/login", 
            {
            username: this.state.username,
            password: this.state.password
            }
        )
        .then(response => {
            if (response.status === 200) {
                this.props.handleSuccessfulAuth(response.data)
                localStorage.setItem("authorization", response.data.jwt);
                console.log("TEST: " +
                localStorage.getItem("authorization").toString())
            }
        }).catch(error => {
        console.error(error)
        })

    }

    render() {

        if(localStorage.getItem("authorization")!=null){
            return <Redirect to='/home'  />
        }

        const {username, password} = this.state
        return(
            <div className="loginSiteWrapper">
                <form className="loginFormWrapper" onSubmit={this.handleSubmit}>
                    <label className="loginFormLabels">EMAIL</label>
                    <input className="loginFormInput" type="text" name="username" placeholder="" value={username} onChange={this.handleChange}/>
                    <label className="loginFormLabels"> PASSWORD</label>
                    <input className="loginFormInput" type="password" name="password" placeholder="" value={password} onChange={this.handleChange}/>
                    
                    <div className="loginButtonsWrapper">
                    <button className="forgottenPasswordFormButton" type="submit" value="Submit">FORGOTTEN PASSWORD?</button>
                    <button className="signInFormButton" type="submit" value="Submit">SIGN IN</button>
                    <button className="signInFormButton" type="submit" value="Submit">SIGN UP</button>
                   </div>
                </form>
            </div>
        )
    }
}

export default Login;

/* <div className={this.state.loginFormWrapperClassName}>
<form className={this.state.loginFormClassName} onSubmit={this.handleSubmit}>
    <label className={this.state.loginFormLabelsClassName}>EMAIL</label>
    <input className={this.state.loginFormInputClassName} type="text" name="username" placeholder="" value={username} onChange={this.handleChange}/>
    <label className={this.state.loginFormLabelsClassName}> PASSWORD</label>
    <input className={this.state.loginFormInputClassName}  type="password" name="password" placeholder="" value={password} onChange={this.handleChange}/>
    
    <div className="loginButtonsWrapper">
    <button className={this.state.forgottenPasswordFormButtonClassName} type="submit" value="Submit">FORGOTTEN PASSWORD?</button>
    <button className={this.state.signInFormButtonClassName} type="submit" value="Submit">SIGN IN</button>
   </div>
</form>

</div> */
