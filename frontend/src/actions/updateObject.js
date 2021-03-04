import Axios from 'axios'
import "../interceptor/interceptor"

export const updateObject = ({object, data}) => async dispatch => {

    const response = await Axios({
        method: 'PATCH',
        url: `http://localhost:8080/api/${object}/update`,
        data: data
    })

    console.log(response)

    dispatch({
        type: "UPDATE_OBJECT",
        payload: response.data
    })
}
