import React, {Component, Components} from 'react'
import CalendarTable from "./calendarTable"

 export default class Calendar extends Component {

    render () {
        return(
            <div>
                <h2>Calendar</h2>
                <CalendarTable />
            </div>
        )
    }
}
