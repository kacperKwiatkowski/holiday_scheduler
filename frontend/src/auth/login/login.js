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

      initialState = {email: '', password: ''}
    
      handleChange = event => {
        this.setState({
        [event.target.name]: event.target.value 
        })
      }
    
      handleSubmit = (event) => {

        event.preventDefault()

        const credentials = {
            email: this.state.email,
            password: this.state.password
        };

          const formData = new FormData();
          formData.append('details', JSON.stringify(credentials));

          console.log(formData.getAll.toString)

          Axios.post("http://localhost:8080/login/credentials", formData, {
                  headers: {
                      "Content-Type": "multipart/form-data"
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

        const {email, password} = this.state
        return(
            <div className="loginSiteWrapper">
                <form className="loginFormWrapper" onSubmit={this.handleSubmit}>
                    <label className="loginFormLabels">EMAIL</label>
                    <input className="loginFormInput" type="email" name="email" placeholder="" value={email} onChange={this.handleChange}/>
                    <label className="loginFormLabels"> PASSWORD</label>
                    <input className="loginFormInput" type="password" name="password" placeholder="" value={password} onChange={this.handleChange}/>
                    
                    <div className="loginButtonsWrapper">
                    <button className="forgottenPasswordFormButton" type="submit" value="Submit">FORGOTTEN PASSWORD?</button>
                    <button className="signInFormButton" type="submit" value="Submit">SIGN IN</button>
                   </div>
                </form>
            </div>
        )
    }
}
