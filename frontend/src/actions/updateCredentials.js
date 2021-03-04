import Axios from 'axios'
import "../interceptor/interceptor"

export const updateCredentials = (params) => async dispatch => {


    console.log(params)

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/credentials/${params.id}/${params.object}/change`,
        params: {
            oldCredential: params.oldCredential,
            newCredential: params.newCredential,
            newCredentialRepeated: params.newCredentialRepeated
        }
    })

    console.log(response)

    dispatch({
        type: "UPDATE_CREDENTIALS",
        payload: response.data
    })
}
