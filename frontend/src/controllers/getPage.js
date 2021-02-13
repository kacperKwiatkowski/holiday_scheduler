import Axios from 'axios'

const fetchUsers = (props) => {
    Axios.get(`http://localhost:8080/api/${props.object}/page`,{
        params: {
            pageNum: props.pagination.pageNum - 1,
            pageSize: props.pagination.pageSize,
            sortBy: props.pagination.sortBy,
            sortOrder: props.pagination.sortOrder,
            filter: props.pagination.filter
        }})
      .then(res => {
        console.log(res)
        return res.data;
      })
      .catch(err => {
          console.log(err)
      });
    }

    export default fetchUsers;