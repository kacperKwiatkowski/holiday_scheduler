import Axios from 'axios'
import "../interceptor/interceptor"

export const fetchNationalHolidays = () => async dispatch =>  {

    const response = await Axios({
        method: 'GET',
        url: `http://localhost:8080/api/nationalholiday/read`
    })

    dispatch({
        type: "FETCH_NATIONAL_HOLIDAYS",
        payload: response.data
    })
}
