import Axios from "axios";
import interceptor from "../interceptor/interceptor"

export const fetchEachTeam = () => async dispatch => {

    const response = await Axios.get(`http://localhost:8080/api/team/read`)

    dispatch({
        type: "FETCH_EACH_TEAM",
        payload: response.data
    })
}
