import React, {useState} from "react";
import NationalHolidayUpload from "../forms/nationalHolidayUpload"
import NationalHolidayManagement from "../forms/nationalHolidayManagement"


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
            </ul>
            <div className="settingsForm">
                {
                    {
                        'UPLOAD_NATIONAL_HOLIDAY': <NationalHolidayUpload />,
                        'MANAGE_NATIONAL_HOLIDAY': <NationalHolidayManagement />
                    }[form]
                }
            </div>
        </div>          

    )
} 
export default SettingsContent;
