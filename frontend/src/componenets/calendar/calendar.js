import React, {Component, Components} from 'react'
import CalendarTable from "./calendarTable"
import Controls from "../controls/controls"

 export default class Calendar extends Component {

    render () {
        return(
            <div>
                <Controls header = {"Calendar"}/>
                <CalendarTable />
            </div>
        )
    }
}
