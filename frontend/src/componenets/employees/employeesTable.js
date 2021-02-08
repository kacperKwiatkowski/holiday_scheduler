import Axios from "axios";
import React, {useState, useEffect} from "react";
import { FaPen, FaTimes } from "react-icons/fa";
import "../../styles/style.css"

const User = () => {

    const[users, setUsers] = useState([]);
    const [disabled, setDisabled] = useState(false);

    function handleUpdate(index) {
      console.log("CLICKED!" + index)
    }


    function handleDelete(index) {
        console.log("CLICKED!" + index)
    }
  

    useEffect(() => {
        fetchUsers();
    }, [])

    const fetchUsers = async () => {
        Axios.get(`http://localhost:8080/api/user/page`,{
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

    const renderTableHead = () => {
        return(
            <tr>
                <th>
                    First Name
                </th>

                <th>
                    Last Name
                </th>

                <th>
                    E-Mail
                </th>

                <th>
                    Status
                </th>

                <th>
                    Days off left
                </th>

                <th>
                    Action
                </th>
            </tr>
        )
    }

    const renderTableBody = () => {
        return users.map((user, index) => {
            return (
                        <tr key={index}>
                            <td>
                                {user.firstName}
                            </td>

                            <td>
                                {user.lastName} 
                            </td>

                            <td>
                                {user.email}
                            </td>

                            <td>
                                {user.roleType}
                            </td>

                            <td>
                                {user.daysOffLeft}
                            </td>
                            <td className="actionButtonsWrapper">
                                <button className="editButton" onClick={handleUpdate(index)}><FaPen className="editButtonIcon"/></button>
                                <button className="deleteButton" onClick={handleDelete(user.id)}><FaTimes className="deleteButtonIcon"/></button>
                            </td>
                        </tr>
                )
            }
        )
    }

    const render = () => {
        return (
            <div>
                <table className="tables">
                    <thead>
                        {renderTableHead()}
                    </thead>
                    <tbody>
                        {renderTableBody()}
                    </tbody>
                </table>
            </div>
        )
    }

    return render();
}

export default User








