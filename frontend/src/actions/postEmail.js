import Axios from 'axios'

export const postEmail = ({id, data}) => async dispatch => {

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/email/send/${id}`,
        params: {
            address: data.address,
            title: data.title,
            content: data.content
        }
    })

    dispatch({
        type: "POST_EMAIL",
        payload: response.data
    })
}
