import Axios from 'axios'
import interceptor from "../interceptor/interceptor"

const uploadNationalHolidays = async ({year, key}) =>  {

    const response = await Axios({
        method: 'POST',
        url: `http://localhost:8080/api/nationalholiday/download/${year}/${key}`
    })

    return response.data;
}

export default uploadNationalHolidays;