import React, {Component} from "react";
import { linksmenu } from "./linksmenu"
import { Link } from "react-router-dom"
import "../../styles/style.css"

const Headerbar = () => {

    const logout = () => {
        localStorage.clear();
        window.location.href = "/";
        console.log("LOGOUT CALL")
      }

        return(
            <div className="headerWrapper">
                <div className="logo">jAvalanche</div>
                <ul className="linksmenuNav">
                    {linksmenu.map((item, index) => { return (
                        <li className="linksmenuLinks linksShadow" key={index}>          
                            <Link to={item.url}>{item.title}</Link>                          
                        </li>
                    )})}
                </ul>
                <div className="profileLinks">
                    <button className="headerbarLogOutButton linksShadow">PROFILE</button>
                    <button onClick={logout} className="headerbarLogOutButton linksShadow">LOG OUT</button>
                </div>
            </div>
        )
}

export default Headerbar;


