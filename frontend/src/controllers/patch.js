import Axios from 'axios'

const Patch = (props) => {

    Axios({
        method: 'PATCH',
        url: `http://localhost:8080/api/${props.object}/update`,
        data: props.data
    })
    .then(res => {
        console.log(res)
        return res.data;
    })
    .catch(err => console.log(err))
}

export default Patch;