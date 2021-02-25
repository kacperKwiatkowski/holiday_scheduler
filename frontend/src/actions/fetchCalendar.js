import Axios from "axios";

import interceptor from "../interceptor/interceptor"

export const fetchCalendar = (pagination) => async dispatch => {
    const response = await Axios.get("http://localhost:8080/calendar/page", {
        params:{
            // pageNo: 0,
            // pageSize: 10,
            // sortBy: 'id',
            // sortOrder: 'ASC',
            // month: 2,
            // year: 2021
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

