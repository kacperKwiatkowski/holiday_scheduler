import Axios from "axios";
import React, {useState, useEffect} from "react";
import Modal from "./modal";
import Controls from "./controls"
import { FaPen, FaTimes } from "react-icons/fa";
import "../styles/style.css"

const User = () => {

    const[users, setUsers] = useState([]);
    const[pagination, setPagination] = useState({
        pageNum: 1,
        pageSize: 5,
        sortBy: 'firstName',
        sortOrder: 'ASC',
        filter: ''
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    useEffect(() => {
        fetchUsers()
    }, [pagination])

    const fetchUsers = async () => {
        await Axios.get(`http://localhost:8080/api/user/page`,{
            params: {
                pageNum: pagination.pageNum - 1,
                pageSize: pagination.pageSize,
                sortBy: pagination.sortBy,
                sortOrder: pagination.sortOrder,
                filter: pagination.filter
            }})
          .then(res => {
            console.log(res)
            setUsers(res.data)
          })
          .catch(err => {
              console.log(err)
              setPagination({
                pageNum: 1,
                pageSize: 5,
                sortBy: 'id',
                sortOrder: 'ASC',
                filter: ''
              })
          });
    }

    const renderTable = () => {
        return (
            <table className="tables">
                <thead>
                    {renderTableHead()}
                </thead>
                <tbody>
                    {renderTableBody()}
                </tbody>
            </table>
        )
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
                                <button className="editButton" onClick={() => setModalData({active: false, data: user, action: "UPDATE"})}><FaPen className="editButtonIcon"/></button>
                                <button className="deleteButton" onClick={() => setModalData({active: false, data: user, action: "DELETE"})}><FaTimes className="deleteButtonIcon"/></button>
                            </td>
                        </tr>
                )
            }
        )
    }

    const render = () => {
        return (
            <div>
                <Controls 
                    header = {"Employees"} 
                    setPagination={setPagination}
                />
                {renderTable()}
                <div 
                    className={modalData.active ? 'modalHiddenPosition modalBackground': 'modalVisablePosition modalBackground'} 
                    //onClick={() => setModalData({active: true, data: "", task: ""})}
                >
                    <Modal 
                        modalData={modalData}
                        setModalData={setModalData}  
                    />
                </div>

            </div>
        )
    }

    return render();
}

export default User








