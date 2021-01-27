import React, {Component} from "react";
import Axios from "axios";

import "./login.css"
import "../../App.css"

export default class Login extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }

      initialState = {username: '', username: ''}
    
      handleChange = event => {
        this.setState({
        [event.target.name]: event.target.value 
        })
      }
    
      handleSubmit = (event) => {

        event.preventDefault()

        const credentials = {
            username: this.state.username,
            password: this.state.password
        };

          const formData = new FormData();
          formData.append('details', JSON.stringify(credentials));

          Axios.post("http://localhost:8080/login", formData, {
                  headers: {
                      "Content-Type": "multipart/form-data",
                      "Access-Control-Allow-Headers": "Accept",
                      "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                      "Access-Control-Allow-Origin": "http://localhost:8080"
                  }
              }
          )
              .then(response => {
                  if (response.status === 200) {
                      this.setState(this.initialState);
                      this.props.handleSuccessfulAuth(response.data)
                      console.log("Response from spring: " + response);
                  }
              }).catch(error => {
                console.error(error)
              })
      }

    render() {

        const {username, password} = this.state
        return(
            <div className="loginSiteWrapper">
                <form className="loginFormWrapper" onSubmit={this.handleSubmit}>
                    <label className="loginFormLabels">EMAIL</label>
                    <input id="username" className="loginFormInput" type="text" name="username" placeholder="" value={username} onChange={this.handleChange}/>
                    <label className="loginFormLabels"> PASSWORD</label>
                    <input id="password" className="loginFormInput" type="password" name="password" placeholder="" value={password} onChange={this.handleChange}/>
                    
                    <div className="loginButtonsWrapper">
                    <button className="forgottenPasswordFormButton" type="submit" value="Submit">FORGOTTEN PASSWORD?</button>
                    <button className="signInFormButton" type="submit" value="Submit">SIGN IN</button>
                   </div>
                </form>
            </div>
        )
    }
}
