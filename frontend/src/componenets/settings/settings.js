import Axios from "axios";
import React, {useState, useEffect} from "react";

const Settings = () => {

    const[users, setUsers] = useState([]);

    useEffect(() => {
        fetchUsers();
    }, [])

    const fetchUsers = async () => {
        Axios.get(`http://localhost:8080/api/user/page`, {
            params: {
                pageNo: 0,
                pageSize: 10,
                sortBy: 'id',
                sortOrder: 'ASC'
            }})
          .then(res => {
            console.log(res)
            setUsers(res.data)
          });
    }

    const renderTable = () => {
        return(
            <table>
                <thead>
                    <tr>
                        {renderTableHead()}
                    </tr>
                </thead>
                <tbody>
                    {renderTableBody()}
                </tbody>
            </table>
        )
    }

    const renderTableHead = () => {
            return Object.entries(users[0]).map(([key]) => {
                return (
                        <td>
                            {key}
                        </td>
                )
            }
        )
    }

    const renderTableBody = () => {
        return users.map((user) => {
            return Object.entries(user).map(([key, value]) => {
                return (
                    <tr>
                        <th>
                            {value}
                        </th>
                    </tr>
                )
            }
        )
    })}

    return renderTable();
}

export default Settings