import Axios from 'axios'

export const deleteObject = ({object, id}) => async (dispatch, getState) => {

    const response = await Axios({
        method: 'DELETE',
        url: `http://localhost:8080/api/${object}/delete/${id}`
    })
    .then(res => {
        console.log(res)
    })
    .catch(err => console.log(err))

    dispatch({
        type: "DELETE_OBJECT",
        payload: response
    })
}