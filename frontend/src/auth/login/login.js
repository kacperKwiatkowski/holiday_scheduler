import React, {Component} from "react";
import Axios from "axios";

import "../../styles/style.css"
import "../../App.css"

export default class Login extends Component {

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

        const credentials = {
            username: this.state.username,
            password: this.state.password
        };

        Axios.post("http://localhost:8080/login", 
              {
                username: this.state.username,
                password: this.state.password
              }
          )
          .then(response => {
              if (response.status === 200) {
                  this.setState(this.initialState);
                  this.props.handleSuccessfulAuth(response.data)
                  console.log("Response from spring: " + response);
                  console.log(response.data.jwt)
              }
          }).catch(error => {
            console.error(error)
          })

          // const formData = new FormData();
          // formData.append('details', JSON.stringify(credentials));

          // console.log(formData.getAll.toString)

          // Axios.post("http://localhost:8080/login", formData, {
          //         headers: {
          //             "Content-Type": "multipart/form-data",
          //           //   "X-Requested-With": "XMLHttpRequest",
          //           //   "Access-Control-Allow-Headers": "Accept, Origin, Content-Type, Authorization, X-Requested-With, X-Auth-Token",
          //           //   "Access-Control-Allow-Origin": "*"
          //         }
          //     }
          // )
          //     .then(response => {
          //         if (response.status === 200) {
          //             this.setState(this.initialState);
          //             this.props.handleSuccessfulAuth(response.data)
          //             console.log("Response from spring: " + response);
          //             console.log(response.data.token)
          //         }
          //     }).catch(error => {
          //       console.error(error)
          //     })
      }

    render() {

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
                   </div>
                </form>
            </div>
        )
    }
}
