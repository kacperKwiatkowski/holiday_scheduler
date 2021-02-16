import React, {useState, useEffect} from "react";
import { connect, useDispatch, useSelector} from 'react-redux';
import { fetchUsers } from '../actions/userActions'
import Controls from "../componenets/controls"
import Modal from "../componenets/modal";
import Table from "../componenets/table";

const Employees = () => {

    const dispatch = useDispatch();
    const users = useSelector((state) => state)
    const[pagination, setPagination] = useState({
        pageNum: 1,
        pageSize: 5,
        sortBy: 'firstName',
        sortOrder: 'ASC',
        filter: ''
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});


    useEffect(() => {
        dispatch(fetchUsers(pagination))
    }, [pagination])

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
            <Modal 
                modalHeader={`${modalData.action} EMPLOYEE's`}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )
}

export default Employees;








