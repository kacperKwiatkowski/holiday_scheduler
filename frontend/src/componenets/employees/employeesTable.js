import Axios from "axios";
import React, {useEffect, useState} from "react";

export const User = () => {

    const user = {
        id: '',
        email: '',
        firstName: '',
        lastName: '',
        roleType: '',
        daysOffLest: ''
    }

    const [users, setUsers] = useState([])

    const fetchUsers = () => {
        Axios.get(`http://localhost:8080/user/read/all`, user)
          .then(res => {
            console.log(res)
            setUsers(res.data)
          });
    }

    useEffect(() => {
        fetchUsers();
    }, []);

    return users.map((user, index) => {
        return (
            <div>
                <h1>Inside of users</h1>
                <ul>
                    <li>user.firstName</li>
                </ul>
            </div>
        )
    })
}

export default User



