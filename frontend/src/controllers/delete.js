import Axios from 'axios'

const Delete = (props) => {

    Axios({
        method: 'DELETE',
        url: `http://localhost:8080/api/${props.object}/delete/${props.id}`
    })
    .then(res => {
        console.log(res)
        return res.data;
    })
    .catch(err => console.log(err))
}

export default Delete;