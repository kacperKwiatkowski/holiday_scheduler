import Axios from "axios";
import React, {Component} from "react";
import "./calendar.css"

class Calendar extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
    
          dates: this.initialValues
        }
    }

    initialState = {
        dates : new Date().toLocaleString()
    }

    render () {

        return (
            <div className="calendarWrapper">
                <table className="calendarTable">
                    <thead>
                        <tr>
                            <td>
                                EMPLOYEE
                            </td>
                            {this.renderTableHead()}
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        )
    }

    renderTableHead () {

        const currentDate = new Date();

        const currentDayOfMonth = currentDate.getDate();
        const currentMonth = currentDate.getMonth() + 1; // Be careful! January is 0, not 1
        const currentYear = currentDate.getFullYear();
        
        const dateString = currentDayOfMonth + "-" + (currentMonth + 1) + "-" + currentYear;
        var i = 0;
        var dates = []
        var monthLength = daysInMonth(currentMonth, currentYear)

        for(i = 0; i < monthLength; i++){
            dates[i] = (i + 1) + "-" + (currentMonth + 1) + "-" + currentYear;;
        }

        return dates.map((date) => {
            return (
                            <td>
                                {date}
                            </td>
                )
            }
        )
    }
      
}

export default Calendar

function daysInMonth (month, year) { 
    return new Date(year, month, 0).getDate(); 
} 