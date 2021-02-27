import Axios from "axios";

import Headerbar from "../componenets/headerbar"
import SettingsControls from "../componenets/settingsControls"
import SettingsContent from "../componenets/settingsContent"

const Settings = () => {


    return(
        <div>
            <Headerbar /> 
            <SettingsControls 
                header = {"Settings"}
            />
            <SettingsContent />

        </div>
    )
}

export default Settings