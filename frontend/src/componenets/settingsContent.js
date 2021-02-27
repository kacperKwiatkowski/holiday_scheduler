import React, {useState, useEffect} from "react";
import NationalHolidayUpload from "../forms/nationalHolidayUpload"
import NationalHolidayManagement from "../forms/nationalHolidayManagement"


const SettingsContent = () => {

    const [form, setForm] = useState("UPLOAD_NATIONAL_HOLIDAY")

    const switchForms = (form) => {
        switch (form){
            case "UPLOAD_NATIONAL_HOLIDAY":
                return <NationalHolidayUpload />
            case "MANAGE_NATIONAL_HOLIDAY":
                return <NationalHolidayUpload />
            default:
                return null;
        }
    }

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
