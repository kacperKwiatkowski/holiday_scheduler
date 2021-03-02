import Axios from "axios";
import interceptor from "../interceptor/interceptor"

export const fetchTeam = (id) => async dispatch => {

    console.log(id)

    const response = await Axios.get(`http://localhost:8080/api/team/read/${id}`)

    dispatch({
        type: "FETCH_TEAM",
        payload: response.data
    })
}