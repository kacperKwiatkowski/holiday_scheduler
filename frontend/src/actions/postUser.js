import Axios from 'axios'
import "../interceptor/interceptor"

export const postUser = ({data}) => async dispatch => {

    console.log(data)

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/user/create`,
        data: data
    })

    dispatch({
        type: "POST_NEW_USER",
        payload: response.data
    })
}
