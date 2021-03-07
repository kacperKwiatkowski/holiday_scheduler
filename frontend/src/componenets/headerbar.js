import React, {useState} from "react";
import { Link } from 'react-router-dom';
import HeaderDropdown from "./headerDropdown";

const Headerbar = () => {


    const[dropDownStatus, setDropDownStatus] = useState(false)


    const logout = () => {
        localStorage.clear();
        window.location.href = "/";
      }
    
    const toogleDropDown = () => {
        setDropDownStatus(!dropDownStatus)
    }

      const linksmenu = [

        {
            title: 'CALENDAR',
            url: "/home",
            cName: 'sidebarNav',
            access: "EMPLOYEE"
        },
        {
            title: 'EMPLOYEES',
            url: "/employees",
            cName: 'sidebarNav',
            access: "HR"
        },
        {
            title: 'TEAM',
            url: "/team",
            cName: 'sidebarNav',
            access: "HR"
        },
        {
            title: 'TEAMS',
            url: "/teams",
            cName: 'sidebarNav',
            access: "HR"
        },
        {
            title: 'VACATIONS',
            url: "/vacations",
            cName: 'sidebarNav',
            access: "HR"
        },
        {
            title: 'SETTINGS',
            url: "/settings",
            cName: 'sidebarNav',
            access: "ADMIN"
        }
    ]

        return(
            <header className={"headerbarWrapper"}>
                <div className="navbarWrapper">
                    <div className="logo">jAvalanche</div>
                    <ul className="linksmenuNav">
                        {linksmenu.map((item, index) => { return (
                            <li className="linksmenuLinks linksShadow" key={index}>          
                                <Link exact to={item.url}>{item.title}</Link>                          
                            </li>
                        )})}
                    </ul>
                    <div className="navbarWrapperLinks">
                        <button  onClick={() => toogleDropDown()} className="navbarWrapperLogOutButton linksShadow">PANEL</button>
                        <button onClick={logout} className="navbarWrapperLogOutButton linksShadow">LOG OUT</button>
                    </div>
                </div>
                <div >
                    <HeaderDropdown 
                        dropDownStatus = {dropDownStatus}
                    />
                </div>
 
            </header>

        )
}

export default Headerbar;


