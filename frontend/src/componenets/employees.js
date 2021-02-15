import React, {useState, useEffect} from "react";
import Axios from "axios";
import Controls from "./controls"
import Modal from "./modal";
import Table from "./table.js";

const Employees = () => {

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
    }, [pagination, modalData])

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

    const render = () => {
        return (
            <div>
                <Controls 
                    header = {"Employees"} 
                    setPagination={setPagination}
                />
                <Table 
                    data = {users}
                    headers={["First Name", "Last Name", "E-Mail", "Status", "Days Off Left"]}
                    setModalData={setModalData}  
                />
                <div 
                    className={modalData.active ? 'modalHiddenPosition modalBackground': 'modalVisablePosition modalBackground'} 
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

export default Employees








