import React, {useState, useEffect} from "react";
import Axios from "axios";
import "../interceptor/interceptor"

const Calendar = ({records, calendarPagination}) => {


    const [nationalHolidays, setNationalHolidays] = useState()

    useEffect(() => {
        fethcNationalHolidays()
    }, [calendarPagination])

    const fethcNationalHolidays = () => Axios.get("http://localhost:8080/api/calendar/nationalHolidays", {
    params: {
        month: calendarPagination.month,
        year: calendarPagination.year
    }})
    .then(
        (res) => {
            setNationalHolidays(res.data)
        }
    )

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
                return 'unpaidVacationButton'
            case 'SICK':
                return 'payedSickButton'
            case 'MATERNITY':
                return 'payedMaternityButton'
            case 'BEREAVEMENT':
                return 'payedBereavementButton'
            case 'SABBATICAL':
                return 'payedSabbaticalButton'
            case 'NONE':
                return "noVacation"
            default:
                return "nationalHoliday"
          }
    }

    const mapNationalHolidays = (dates) => {

        if(Array.isArray(nationalHolidays) && nationalHolidays.length){
            console.log(typeof nationalHolidays === undefined)
            nationalHolidays.map(n => {
                dates[parseInt(n.holidayDate.substring(8))-1].nationalHoliday = n.name
            })

            return dates;
        } 

        return dates
    }

    let daysOfMonth = mapDaysOfWeek()
    
    function mapDaysOfWeek() {


        const currentMonth = calendarPagination.month;
        const currentYear = calendarPagination.year;
    
        let days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    
        const dates = []
        let i
        let monthLength = daysInMonth(currentMonth, currentYear)
    
        for(i = 1; i <= monthLength; i++){
            let currentDay = returnDayFormat(i);

            dates.push(
                {
                    date: currentDay + "/" + currentMonth + "/" + currentYear,
                    day: days[new Date(currentYear, currentMonth-1, i).getDay()],
                    nationalHoliday: ""
                }
            )
        }

        console.log(dates)

        return mapNationalHolidays(dates);
    }

    const renderTableHead = () => {

        return( daysOfMonth.map((date, index) => {
            return (
                    <th 
                        className={date.nationalHoliday==="" ? "calendarHeadCell" : "calendarHeadCell calendarHolidayHeadCell"}
                        key={index}
                    >
                        {date.nationalHoliday==="" ? date.date : date.nationalHoliday}
                    </th>
                )
            }
        ))
    }

    const renderTableBody = () => {

        return( records.map((record, index) => {
            return(
            <tr 
                key={index}
            >
                <th>
                    <button className="calendarNameButton">
                    {record.userDto.firstName} {record.userDto.lastName}
                    </button>
                </th>
                {renderTableRowsDate(record.holidayStatus)}
            </tr>
            )
        }))
    }

    const renderTableRowsDate = (holidayStatus) => {
        return( 
            holidayStatus.map((date, index) => {
                let buttonClassName = returnVacationTypeTag(date)
                if(index<daysOfMonth.length){
                    return(
                        <td key={index}>
                            <div className="dateTag">{daysOfMonth[index].date}</div>
                            <button className={"vacationButton " + buttonClassName}>{date}</button>
                        </td>
                    )
                } else return null
        }))
    }

    return (
        <table className="calendarTable">
            <thead>
                <tr>
                    <th className="calendarHeadCell">
                        Employees
                    </th>
                    {renderTableHead()}
                </tr>
            </thead>
            <tbody>
                {renderTableBody()}
            </tbody>
        </table>
    )
}

export default Calendar