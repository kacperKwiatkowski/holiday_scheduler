import Axios from 'axios'

export const updateObject = ({object, data}) => dispatch => {

    const response = Axios({
        method: 'PATCH',
        url: `http://localhost:8080/api/${object}/update`,
        data: data
    })
    .then(res => {
        console.log(res)
    })
    .catch(err => console.log(err))

    dispatch({
        type: "UPDATE_OBJECT",
        payload: response
    })
}
