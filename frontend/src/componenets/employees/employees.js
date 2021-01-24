import React, {Component, Components} from 'react'
import Users from "./employeesTable"

 export default class employees extends Component {

    render () {
        return(
            <div>

                <h1>employees</h1>
                <Users />
                <h1>employees</h1>
            </div>
        )
    }
}
