import axios from "axios";
import React, {useEffect, useState} from "react";
import "../styles/style.css"

const SettingsControls = ({header}) => {


    return (
        <form className="controlsWrapper">
            <div className="pageHeader">{header}</div>


        </form>
        )
} 

export default SettingsControls;