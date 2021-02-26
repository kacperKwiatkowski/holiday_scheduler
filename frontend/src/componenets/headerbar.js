import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { Link } from 'react-router-dom';

import { fetchLoggedUser } from '../actions/fetchLoggedUser'

import Logo from './Logo.png';

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
                <div className={dropDownStatus ? "profile-dropDown profile-dropDown-visable" : "profile-dropDown profile-dropDown-hidden"}>
                    <table className="profile-dropDown-table">
                        <tbody>
                            <tr>
                                <td rowspan="6">
                                    <img src={Logo} />
                                </td>
                                <td>FIRST NAME: {loggedUser.firstName}</td>
                            </tr>
                            <tr>
                                <td>LAST NAME: {loggedUser.lastName}</td>
                            </tr>
                            <tr>
                                <td>E:MAIL: {loggedUser.email}</td>
                                </tr>
                            <tr>
                                <td>ROLE: {loggedUser.roleType}</td>
                            </tr>
                            <tr>
                                <td>REMAINING DAYS OFF: {loggedUser.daysOffLeft}</td>
                                </tr>
                            <tr>
                                <td>TEAM: </td>
                            </tr>
                        </tbody>

                    </table>
                    <table className="profile-dropDown-buttons-wrapper">
                        <tbody>
                            <tr>
                                <td>
                                    <button className="profile-dropDown-button">SEND AN EMAIL</button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button className="profile-dropDown-button">CHANGE PASSWORD</button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button className="profile-dropDown-button">REQUEST VACATION</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
 
            </header>

        )
}

export default Headerbar;


