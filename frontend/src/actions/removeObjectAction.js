import Axios from 'axios'
import "../interceptor/interceptor"

export const removeObjectAction = ({genre, object, id}) => async (dispatch) => {


    alert(genre, object, id)
    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/${genre}/${object}/remove/${id}`
    })

    dispatch({
        type: "REMOVE_OBJECT",
        payload: response
    })
}