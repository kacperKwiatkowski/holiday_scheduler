import "./headerbar.css"

import { linksmenu } from "./linksmenu"
import { Link } from "react-router-dom"

export const Headerbar = () => {
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
                    <button className="headerbarLogOutButton linksShadow">LOG OUT</button>
                </div>

        </div>
    )
}
export default Headerbar;