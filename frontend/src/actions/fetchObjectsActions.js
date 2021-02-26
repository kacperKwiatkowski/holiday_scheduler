import Axios from "axios";
import interceptor from "../interceptor/interceptor"

export const fetchObjects = ({object, pagination}) => async dispatch => {

    const response = await Axios.get(`http://localhost:8080/api/${object}/page`,{
        params: {
            pageNum: pagination.pageNum - 1,
            pageSize: pagination.pageSize,
            sortBy: pagination.sortBy,
            sortOrder: pagination.sortOrder,
            filter: pagination.filter
        }})

    dispatch({
        type: "FETCH_OBJECTS",
        payload: response.data
    })
}

