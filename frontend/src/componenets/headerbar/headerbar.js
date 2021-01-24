import "./headerbar.css"

import { linksmenu } from "./linksmenu"
import { Link } from "react-router-dom"

export const Headerbar = () => {
    return(
        <div>

            <div className="headerbarWrapper">
                <div className="logo">jAvalanche</div>
                <button className="headerbarLogOutButton linksShadow">LOG OUT</button>
            </div>
            <ul className="linksmenuNav">
                {linksmenu.map((item, index) => { return (
                    <li className="linksmenuLinks linksShadow" key={index}>
                             
                        <Link to={item.url}>{item.title}</Link>                          
                    </li>
                )})}
            </ul>
        </div>
    )
}
export default Headerbar;