import Axios from "axios";

export const fetchCalendar = (pagination) => async dispatch => {
    const response = await Axios.get("http://localhost:8080/calendar/page", {
        params:{
            pageNo: pagination.pageNo,
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

