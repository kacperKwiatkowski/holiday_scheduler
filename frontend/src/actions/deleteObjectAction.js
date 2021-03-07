import Axios from 'axios'
import { useSelector} from 'react-redux';
import "../interceptor/adminActionsInterceptor"
import store from '../store/store';

export const deleteObject = ({object, id}) => async (dispatch) => {

    const response = await Axios({
        method: 'DELETE',
        url: `http://localhost:8080/api/${object}/delete/${id}`
    })

    dispatch({
        type: "DELETE_OBJECT",
        payload: response
    })
}