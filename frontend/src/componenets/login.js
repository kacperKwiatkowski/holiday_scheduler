import React, {useState} from "react";
import { Form, Field } from 'react-final-form'
import { useHistory } from "react-router-dom";
import  { Redirect } from 'react-router-dom'
import Axios from "axios";
import "../interceptor/interceptor"

const Login = () => {

    const history = useHistory();
    const [credentials, setCredentials] = useState({username: '', password: ''})
    const [secondForm, setSecondForm] = useState(false)

    const handleChange = (event) => {
        const value = event.target.value;
        setCredentials({
            ...credentials,
            [event.target.name]: value
        });
        console.log(credentials)
    };
    
    const handleLoginSubmit = async (event) => {

    event.preventDefault()
        
     await authorize();

    }

    const handleRegistrationSubmit = values => {
      }

    const authorize =  () => {
         Axios.post("http://localhost:8080/login", 
            {
            username: credentials.username,
            password: credentials.password
            }
        )
        .then(response => {
            if (response.status === 200) {
                //TODO Store it in a cookie
                localStorage.setItem("authorization", response.data.jwt);
                localStorage.setItem('loggedUser', credentials.username);
                history.go("/home")

            }
        }).catch(error => {
        console.error(error)
        })
    }

    const loginForm = () => {
        return(
            <form className={secondForm ? "loginFormWrapper loginFormShiftedWrapper" : "loginFormWrapper"} onSubmit={event => handleLoginSubmit(event)}>
                <div className="loginFormHeader">
                    LOG IN
                </div>
                <div className="loginFormContent">
                    <label className="loginFormLabels">EMAIL</label>
                    <input className="loginFormInput" type="text" name="username" placeholder="" value={credentials.username} onChange={event => handleChange(event)}/>
                    <label className="loginFormLabels"> PASSWORD</label>
                    <input className="loginFormInput" type="password" name="password" placeholder="" value={credentials.password} onChange={event => handleChange(event)}/>
                </div>
                <div className="loginButtonsWrapper">
                    <button className="forgottenPasswordFormButton" type="submit" value="Submit">FORGOTTEN PASSWORD?</button>
                    
                    <button className="signInFormButton" type="submit" value="Submit">SIGN IN</button>
                    <button className="signInFormButton" type="button" onClick={() => {setSecondForm(!secondForm)}}>SIGN UP</button>
                </div>
            </form>
        )
    }

    const registerForm = () => {
        return(
            <Form
            onSubmit={handleRegistrationSubmit}
            render={({ onSubmit, form, values }) => (
              <form 
                onSubmit={onSubmit}
                className={secondForm ? "registrationFormWrapper registrationFormShiftedWrapper" : "registrationFormWrapper"}
              >
                <div className="loginFormHeader">
                    REGISTER
                </div>
                <div className="loginFormContent">
                    <label className="loginFormLabels">FIRST NAME:</label>
                    <Field
                      className="loginFormInput" 
                      name="firstName"
                      component="input"
                      type="text"
                    />
                    <label className="loginFormLabels">LAST NAME:</label>
                    <Field
                      className="loginFormInput" 
                      name="lastName"
                      component="input"
                      type="text"
                    />
                    <label className="loginFormLabels">EMAIL:</label>
                    <Field
                      className="loginFormInput" 
                      name="email"
                      component="input"
                      type="email"
                    />
                </div>
                <div className="loginButtonsWrapper">
                    <button
                        className="signInFormButton"
                        type="button"
                        onClick={form.reset}
                    >
                        RESET
                    </button>
                    <button 
                        type="submit"
                        className={`signInFormButton`}
                    >
                        SEND
                    </button>
                </div>
              </form>
            )}
          />
        )
    }

    if(localStorage.getItem("authorization")!=null){
        return <Redirect go to='/home'  />
    } else {
        return(
            <div className="loginSiteWrapper">
                {loginForm()}
                {registerForm()}
            </div>
        )
    }
}

export default Login;

