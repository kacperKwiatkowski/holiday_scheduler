import Axios from "axios";
import React, {Component} from "react";
import "../../styles/style.css"

class Calendar extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
            users: this.initialUsers,
            vacations: this.initialVacations,
            pagination: this.initialPagination,
            dates: this.initialDates,
            initialDate: {
                month: ("0" + (new Date().getMonth() + 1)).slice(-2),
                year: new Date().getFullYear()
            }
        }
    }

    initialUsers = [{ 
        id: '',   
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        levelOfAccess: '',
        daysOffLeft: ''
    }]

    initialVacations = [
        {
            accepted: "",
            firstDay: "",
            id: "",
            lastDay: "",
            leaveType: "",
            userID: "",
        }
    ]

    initialPagination = {
        pageNo: 0,
        pageSize: 10,
        sortBy: 'id',
        sortOrder: 'ASC'
    }

    initialDates = []

    componentDidMount() {
        Axios.get(`http://localhost:8080/user/page?pageNo=0&pageSize=10&sortBy=lastName&sortOrder=ASC`)
          .then(res => {
            console.log(res)
            this.setState({users: res.data})
          });
    }

    
    fetchVacations() {
        
        var month = "02" //this.state.initialDate.month
        var year = this.state.initialDate.year

        console.log(month + " " + year)

        var URL = "http://localhost:8080/vacation/read/required?month=" + month + "&year=" + year

        const formData = new FormData();
        formData.append('details', JSON.stringify(this.state.users.map((user) => {return (user.id)})));

        Axios.post(URL , formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        )
        .then(res => {
          console.log(res)
          return res
        }).catch(error => {
            console.error(error)
          })
    }


    render () {

        return (
                <table className="calendarTable">
                    <thead>
                        <tr>
                            <th className="calendarHeadCell">
                                EMPLOYEE
                            </th>
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
        


        return daysOfWeek.map((date) => {
            return (
                        <th className="calendarHeadCell">
                            {date}
                        </th>
                )
            }
        )
    }

    renderTableBody() {

        return( this.state.users.map(user => {
            return(
            <tr>
                <th><button className="calendarNameButton">{user.firstName} {user.lastName}</button></th>
                {this.renderTableRowsDate(user.id)}

            </tr>
            )
        }))
    }

    renderTableRowsDate(id) {


        return( 
            this.state.dates.map((date, index) => {
            return(
                <td>
                    {date}
                    <button className="vacationButton"></button>
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