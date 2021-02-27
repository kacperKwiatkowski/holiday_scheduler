import NationalHolidayUpload from "../forms/nationalHolidayUpload"

const SettingsContent = () => {
    return(
        <div className="settingsWrapper">  
            <ul className="settingsPanel">
                <li className="settingsLink">
                    Upload National Holidays
                </li>
                <li className="settingsLink">
                    Manage National Holidays
                </li>
            </ul>
            <div className="settingsForm">
                <NationalHolidayUpload />
            </div>
        </div>          

    )
} 
export default SettingsContent;