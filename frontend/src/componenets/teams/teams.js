import React, {Component, Components} from 'react'
import Teams from "./teamsTable"
import Controls from "../controls/controls"

 export default class employees extends Component {

    render () {
        return(
            <div>
                
                <Controls header = {"Teams"}/>
                <Teams />
            </div>
        )
    }
}
