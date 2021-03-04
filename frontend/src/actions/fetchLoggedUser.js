import Axios from "axios";
import "../interceptor/interceptor"

export const fetchLoggedUser = (email) => async dispatch => {

    const response = await Axios.get(`http://localhost:8080/api/user/read/logged`,{
        params: {
            email: email
        }
    })

    dispatch({
        type: "FETCH_LOGGED_USER",
        payload: response.data
    })
}
