import React, {useState} from "react";
import NationalHolidayUpload from "../forms/nationalHolidayUpload"
import NationalHolidayManagement from "../forms/nationalHolidayManagement"
import AddUser from "../forms/addUser"
import AddTeam from "../forms/addTeam"



const SettingsContent = () => {

    const [form, setForm] = useState("UPLOAD_NATIONAL_HOLIDAY")

    return(
        <div className="settingsWrapper">  
            <ul className="settingsPanel">
                <li className="settingsLink" onClick={() => {setForm("UPLOAD_NATIONAL_HOLIDAY");}}>
                    Upload National Holidays
                </li>
                <li className="settingsLink" onClick={() => {setForm("MANAGE_NATIONAL_HOLIDAY");}}>
                    Manage National Holidays
                </li>
                <li className="settingsLink" onClick={() => {setForm("ADD_USER");}}>
                    Add user
                </li>
                <li className="settingsLink" onClick={() => {setForm("ADD_TEAM");}}>
                    Add team
                </li>
            </ul>
            <div className="settingsForm">
                {
                    {
                        'UPLOAD_NATIONAL_HOLIDAY': <NationalHolidayUpload />,
                        'MANAGE_NATIONAL_HOLIDAY': <NationalHolidayManagement />,
                        'ADD_USER': <AddUser />,
                        'ADD_TEAM': <AddTeam />
                    }[form]
                }
            </div>
        </div>          

    )
} 
export default SettingsContent;
