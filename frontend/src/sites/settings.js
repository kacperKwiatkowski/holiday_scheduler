import SettingsControls from "../componenets/settingsControls"
import SettingsContent from "../componenets/settingsContent"

const Settings = () => {


    return(
        <div>
            <SettingsControls 
                header = {"Settings"}
            />
            <SettingsContent />

        </div>
    )
}

export default Settings