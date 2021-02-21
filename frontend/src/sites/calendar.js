import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchCalendar } from '../actions/fetchCalendar'
import CalendarControls from "../componenets/calendarControls"
import interceptor from "../interceptor/interceptor"
import ObjectReducer from "../reducers/objectsReducer";

const Calendar = () => {

    const dispatch = useDispatch();
    const records = useSelector((state) => state.recordReducer)
    const [calendarPagination, setCalendarPagination] = useState({       
        pageNo: 0,
        pageSize: 10,
        sortBy: "lastName",
        sortOrder: "ASC",
        month: ("0" + (new Date().getMonth() + 1)).slice(-2),
        year: new Date().getFullYear()
    })

    useEffect(() => {
        dispatch(fetchCalendar(calendarPagination))
    }, [calendarPagination]);

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

    var daysOfMonth = mapDaysOfWeek()
    
    function mapDaysOfWeek() {
        const currentMonth = calendarPagination.month;
        const currentYear = calendarPagination.year;
    
        var days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    
        const dates = []
        var i;
        var monthLength = daysInMonth(currentMonth, currentYear)
    
        for(i = 1; i <= monthLength; i++){
            var currentDay = returnDayFormat(i);

            dates.push(
                {
                    date: currentDay + "/" + currentMonth + "/" + currentYear,
                    day: days[new Date(currentYear, currentMonth-1, i).getDay()]
                }
            )
        }
        return dates;
    }

    const renderTableHead = () => {

        console.log("THESE ARE RECORD")
        console.log(records)
        return( daysOfMonth.map((date, index) => {
            return (
                    <th className="calendarHeadCell">
                        {date.day}
                    </th>
                )
            }
        ))
    }

    const renderTableBody = () => {

        return( records.map((record, index) => {
            return(
            <tr key={index}>
                <th><button className="calendarNameButton">
                    {record.userDto.firstName} {record.userDto.lastName}
                    </button></th>
                {renderTableRowsDate(record.holidayStatus)}
            </tr>
            )
        }))
    }

    const renderTableRowsDate = (holidayStatus) => {

        return( 
            holidayStatus.map((date, index) => {
                var buttonClassName = returnVacationTypeTag(date)
            return(
                <td>
                    <div className="dateTag">{daysOfMonth[index].date}</div>
                    <button className={"vacationButton " + buttonClassName}>{date}</button>
                </td>
            )
        }))
    }

    return (
        <div>
            <CalendarControls 
                header = {"Calendar"}
                setPagination = {setCalendarPagination}
            />
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
        </div>
    )
    
}

export default Calendar

