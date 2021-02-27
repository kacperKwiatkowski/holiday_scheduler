import Axios from "axios";
import React, {useState, useEffect} from "react";

import Headerbar from "../componenets/headerbar"
import SettingsControls from "../componenets/settingsControls"
import SettingsAccordeon from "../componenets/settingsContent"

const Settings = () => {

    return(
        <div>
            <Headerbar /> 
            <SettingsControls 
                header = {"Settings"}
            />
            <SettingsAccordeon />

        </div>
    )
}

export default Settings