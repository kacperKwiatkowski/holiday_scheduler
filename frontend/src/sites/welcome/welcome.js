import React, {Component} from "react";
import Login from "../../auth/login/login";

export class Welcome extends Component{

    constructor(props) {
        super(props);

        this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this);
    }

    handleSuccessfulAuth(data) {
        this.props.handleLogin(data)
        this.props.history.push("/home")
    }


    render() {
    return (
        <div className="">
            <Login handleSuccessfulAuth={this.handleSuccessfulAuth}/>
        </div>
    );
    }
}

export default Welcome;