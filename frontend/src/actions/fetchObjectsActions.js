import Axios from "axios";

export const fetchObjects = ({object, pagination}) => async dispatch => {
    console.log(pagination)
    const response = await Axios.get(`http://localhost:8080/api/${object}/page`,{
        params: {
            pageNum: pagination.pageNum - 1,
            pageSize: pagination.pageSize,
            sortBy: pagination.sortBy,
            sortOrder: pagination.sortOrder,
            filter: pagination.filter
        }})

    dispatch({
        type: "FETCH_USERS",
        payload: response.data
    })
}

