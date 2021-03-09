import Axios from 'axios'
import "../interceptor/interceptor"

export const postObject = ({object, data}) => async dispatch => {

    console.log(data)

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/${object}/create`,
        data: data
    })

    dispatch({
        type: "POST_OBJECT",
        payload: response.data
    })
}
