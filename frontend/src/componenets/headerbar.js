import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { Link } from 'react-router-dom';

import { fetchLoggedUser } from '../actions/fetchLoggedUser'

const Headerbar = () => {


    const[dropDownStatus, setDropDownStatus] = useState(false)
    const dispatch = useDispatch();
    const loggedUser = useSelector((state) => state.loggedUserReducer)

    useEffect(() => {
        dispatch(fetchLoggedUser(localStorage.getItem('loggedUser')))
    }, [])


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
                        <button  onClick={() => toogleDropDown()} className="navbarWrapperLogOutButton linksShadow">PROFILE</button>
                        <button onClick={logout} className="navbarWrapperLogOutButton linksShadow">LOG OUT</button>
                    </div>
                </div>
                <div className={dropDownStatus ? "profile-dropDown profile-dropDown-visable" : "profile-dropDown profile-dropDown-hidden"}>
                    <table className="profile-dropDown-table">
                    <tr>
                        <td rowspan="3">&nbsp;</td>
                            <td>FIRST NAME: {loggedUser.firstName}</td>
                            <td>LAST NAME: {loggedUser.lastName}</td>
                        </tr>
                        <tr>
                            <td>E:MAIL: {loggedUser.email}</td>
                            <td>ROLE: {loggedUser.roleType}</td>
                        </tr>
                        <tr>
                            <td>REMAINING DAYS OFF: {loggedUser.daysOffLeft}</td>
                            <td>EDIT PASSWORD: </td>
                        </tr>
                    </table>
                </div>
 
            </header>

        )
}

export default Headerbar;


