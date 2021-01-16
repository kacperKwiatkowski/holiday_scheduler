import React, {Component} from "react";
import Sidebar from "../../componenets/sidebar/sidebar"
import Headerbar from "../../componenets/headerbar/headerbar"
import Calendar from "../../componenets/calendar/calendar"

export class Home extends Component{

    render() {
    return (
        <div>
            <Sidebar />
            <Headerbar />
            <Calendar />
        </div>
    );
    }
}

export default Home;