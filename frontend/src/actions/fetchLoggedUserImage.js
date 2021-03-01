import Axios from "axios";
import interceptor from "../interceptor/interceptor"

export const fetchLoggedUserImage = ({id, imageUrl}) => async dispatch => {

    const response = await Axios.get(`http://localhost:8080/api/user/${id}/image/download`,{
    })

    dispatch({
        type: "FETCH_LOGGED_USER_IMAGE",
        payload: response.data
    })
}
