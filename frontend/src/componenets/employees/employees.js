import React, {Component, Components} from 'react'
import Controls from "../controls/controls"
import Users from "./employeesTable"

 export default class employees extends Component {

    render () {
        return(
            <div>
                <Controls />
                <Users />
            </div>
        )
    }
}
