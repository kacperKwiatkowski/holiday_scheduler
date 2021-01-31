import React, {Component, Components} from 'react'
import Team from "./teamTable"
import Controls from "../controls/controls"

 export default class team extends Component {

    render () {
        return(
            <div>
                
                <Controls header = {"Team"}/>
                <Team />
            </div>
        )
    }
}
