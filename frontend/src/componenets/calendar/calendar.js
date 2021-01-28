import Axios from "axios";
import React, {Component} from "react";
import "./calendar.css"

class Calendar extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
    
            users: [],
            pagination: this.initialPagination,
            dates: this.initialDates
        }
    }

    initialDates = {
        dates : new Date().toLocaleString()
    }

    initialPagination = {
        pageNo: 0,
        pageSize: 10,
        sortBy: 'id',
        sortOrder: 'ASC'
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/user/page?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASC`)
          .then(res => {
            console.log(res)
            this.setState({users: res.data})
          });
    }


    render () {

        return (
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
                        {this.renderTableBody()}
                    </tbody>
                </table>
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

                        <div className="calendarCell">
                            {date}

                        </div>
                        </td>
                )
            }
        )
    }

    renderTableBody() {
        return( this.state.users.map(user => {
            return(
            <tr>
                <td>{user.firstName} {user.lastName}</td>
            </tr>
            )
        }))
    }
    
}

export default Calendar

function daysInMonth (month, year) { 
    return new Date(year, month, 0).getDate(); 
} 