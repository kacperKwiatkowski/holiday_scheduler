import {sidebarNav} from "./sidebarNav.js"
import "../../styles/style.css"
import { Link } from "react-router-dom"

export const Sidebar = () => {
    return(
        <div className="sidebarWrapper">
            <div className="sidebarLogoWrapper">
                <img src={logo} style={{width: 200}}/>
            </div>
            <ul className="sidebarNav">
                {sidebarNav.map((item, index) => { return (
                    <li className="sidebarNavLinks" key={index}>
                             
                        <Link to={item.url}>{item.title}</Link>                          
                    </li>
                )})}
            </ul>
    
        </div>
    )
}
export default Sidebar;