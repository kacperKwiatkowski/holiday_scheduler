import Axios from 'axios'

export const postVacationRequest = ({id, data}) => async dispatch => {

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/vacation/request/${id}`,
        params: {
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
