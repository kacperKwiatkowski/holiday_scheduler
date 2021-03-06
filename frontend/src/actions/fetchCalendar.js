import Axios from "axios";

import "../interceptor/interceptor"

export const fetchCalendar = (pagination) => async dispatch => {

    console.log(pagination)
    const response = await Axios.get("http://localhost:8080/api/calendar/page", {
        params:{
            filter: pagination.filter, 
            pageNum: pagination.pageNum - 1,
            pageSize: pagination.pageSize,
            sortBy: pagination.sortBy,
            sortOrder: pagination.sortOrder,
            month: pagination.month,
            year: pagination.year
        },
        headers: {
            "Content-Type": "multipart/form-data"
        }})

    dispatch({
        type: "FETCH_CALENDAR",
        payload: response.data
    })
}

