import Axios from "axios";
import React, {Component} from "react";
import Controls from "./controls"
import "../styles/style.css"
import interceptors from "../interceptor/interceptor";

class Calendar extends Component {
    
    state = {
        users: [],
        vacations: [],
        initialDate: {
            month: ("0" + (new Date().getMonth() + 1)).slice(-2),
            year: new Date().getFullYear()
        },
        initialPagination: {
            pageNo: 0,
            pageSize: 10,
            sortBy: "lastName",
            sortOrder: "ASC"
        }
    }
        
    async componentDidMount() {
        
          var URL = "http://localhost:8080/calendar/page"

        await Axios.get(URL, {
            params:{
                pageNo: this.state.initialPagination.pageNo,
                pageSize: this.state.initialPagination.pageSize,
                sortBy: this.state.initialPagination.sortBy,
                sortOrder: this.state.initialPagination.sortOrder,
                month: this.state.initialDate.month,
                year: this.state.initialDate.year
            },
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }).then(res => {
            console.log(res.data)
            this.setState({users: res.data})
        })
    }


    render () {
        return (
            <div>
                <Controls header = {"Calendar"}/>
                <table className="calendarTable">
                <thead>
                    <tr>
                        <th className="calendarHeadCell">
                            Employees
                        </th>
                        {this.renderTableHead()}
                    </tr>
                </thead>
                <tbody>
                    {this.renderTableBody()}
                </tbody>
            </table>
            </div>

        )
    }

    renderTableHead () {

        const initDate = new Date(this.state.initialDate.year, this.state.initialDate.month, 1);

        const currentMonth = this.state.initialDate.month;
        const currentYear = this.state.initialDate.year;

        var days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];

        const dates = []
        const daysOfWeek = []
        var i = 0;
        var monthLength = daysInMonth(currentMonth, currentYear)

        for(i = 1; i <= monthLength; i++){
            var currentDay = returnDayFormat(i);
            dates.push(currentDay + "/" + currentMonth + "/" + currentYear);
            daysOfWeek.push(days[new Date(currentYear, currentMonth-1, i).getDay()])
        }

        this.state.dates = dates;
        


        return daysOfWeek.map((date, index) => {
            return (
                        <th className="calendarHeadCell">
                            {date}
                        </th>
                )
            }
        )
    }

    renderTableBody() {

        return( this.state.users.map((user, index) => {
            return(
            <tr>
                <th><button className="calendarNameButton">{user.userDto.firstName} {user.userDto.lastName}</button></th>
                {this.renderTableRowsDate(user.holidayStatus)}

            </tr>
            )
        }))
    }

    renderTableRowsDate(holidayStatus) {

        return( 
            holidayStatus.map((date, index) => {

                var buttonClassName = returnVacationTypeTag(date)

            return(
                <td>
                    
                    <div className="dateTag">{this.state.dates[index]}</div>
                    <button className={"vacationButton " + buttonClassName}>{date}</button>
                </td>
            )
        }))
    }
    
}

export default Calendar

function daysInMonth (month, year) { 
    return new Date(year, month, 0).getDate(); 
} 

function returnDayFormat(day) {
    return ("0" + day).slice(-2);
}

function returnVacationTypeTag(dayStatus){
    switch (dayStatus) {
        case 'PAYED':
            return 'payedVacationButton'
        case 'UNPAID':
            return 'payedUnpaidButton'
        case 'SICK':
            return 'payedSickButton'
        case 'MATERNITY':
            return 'payedMaternityButton'
        case 'BEREAVEMENT':
            return 'payedBereavementButton'
        case 'SABBATICAL':
            return 'payedSabbaticalButton'
        default:
            return "noVacation"
      }
}