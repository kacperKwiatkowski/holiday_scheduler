import React, {useState, useEffect} from "react";
import { useHistory } from "react-router-dom";
import  { Redirect } from 'react-router-dom'
import { useDispatch} from 'react-redux';
import Axios from "axios";
import interceptor from "../interceptor/interceptor"

const Login = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [credentials, setCredentials] = useState({username: '', password: ''})

    const handleChange = (event) => {
        const value = event.target.value;
        setCredentials({
            ...credentials,
            [event.target.name]: value
        });
        console.log(credentials)
    };
    
    const handleSubmit = async (event) => {

    event.preventDefault()
        
     await authorize();

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

    if(localStorage.getItem("authorization")!=null){
        return <Redirect go to='/home'  />
    } else {
        return(
            <div className="loginSiteWrapper">
                <form className="loginFormWrapper" onSubmit={event => handleSubmit(event)}>
                    <label className="loginFormLabels">EMAIL</label>
                    <input className="loginFormInput" type="text" name="username" placeholder="" value={credentials.username} onChange={event => handleChange(event)}/>
                    <label className="loginFormLabels"> PASSWORD</label>
                    <input className="loginFormInput" type="password" name="password" placeholder="" value={credentials.password} onChange={event => handleChange(event)}/>
                    
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

