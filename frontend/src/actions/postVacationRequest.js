import Axios from 'axios'
import "../interceptor/interceptor"

export const postVacationRequest = ({id, data}) => async dispatch => {

    console.log(data.leaveType)

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/vacation/request`,
        data: {
            userID: id,
            firstDay: data.firstDay,
            lastDay: data.lastDay,
            leaveType: data.leaveType
        }
    })

    dispatch({
        type: "POST_VACATION_REQUEST",
        payload: response.data
    })
}
