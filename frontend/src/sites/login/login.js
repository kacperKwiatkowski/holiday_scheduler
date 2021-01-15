import React from "react";
import Axios from "axios";

import "./login.css"

export class Login extends React.Component {

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

          Axios.post("http://localhost:8080/login/credentials", formData, {
                  headers: {
                      "Content-Type": "multipart/form-data"
                  }
              }
          )
              .then(response => {
                  if (response.data != null) {
                      this.setState(this.initialState);
                      alert("User logged in successfully")
                  }
              })
      }

    render() {

        const {email, password} = this.state
        return(
            <div className="loginSiteWrapper">
                <form className="loginFormWrapper" onSubmit={this.handleSubmit}>
                    <input className="loginFormInput" type="text" name="email" placeholder="email" value={email} onChange={this.handleChange}/>
                    <input className="loginFormInput" type="password" name="password" placeholder="password" value={password} onChange={this.handleChange}/>
                    <button className="loginFormButton" type="submit" value="Submit"/>
                </form>
            </div>
        )
    }

}

export default Login;