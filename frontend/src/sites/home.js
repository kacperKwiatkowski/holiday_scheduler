import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchCalendar } from '../actions/fetchCalendar'
import Headerbar from "../componenets/headerbar"
import CalendarControls from "../componenets/calendarControls"
import Calendar from "../componenets/calendar";

const Home = () => {

    const dispatch = useDispatch();
    const records = useSelector((state) => state.recordReducer)
    const [calendarPagination, setCalendarPagination] = useState({       
        pageNum: 1,
        pageSize: 5,
        sortBy: "lastName",
        sortOrder: "ASC",
        month: ("0" + (new Date().getMonth() + 1)).slice(-2),
        year: new Date().getFullYear()
    })

    useEffect(() => {
        dispatch(fetchCalendar(calendarPagination))
    }, [calendarPagination]);

    return (
        <div>
            <Headerbar /> 
            <CalendarControls 
                header = {"Calendar"}
                setPagination = {setCalendarPagination}
            />
            <Calendar 
                records = {records}
                calendarPagination = {calendarPagination}
            />
        </div>
    )
}

export default Home

