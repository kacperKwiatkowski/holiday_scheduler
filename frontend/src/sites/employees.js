import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchObjects } from '../actions/fetchObjectsActions'
import Headerbar from "../componenets/headerbar"
import TableControls from "../componenets/tableControls"
import Modal from "../componenets/modal";
import UpdateUser from "../forms/updateUser";
import DeleteUser from "../forms/deleteUser";
import Table from "../componenets/table";

const Employees = () => {

    const dispatch = useDispatch();
    const users = useSelector((state) => state.objectReducer)
    const[pagination, setPagination] = useState({
        pageNum: 1,
        pageSize: 5,
        sortBy: 'firstName',
        sortOrder: 'ASC',
        filter: ''
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    useEffect(() => {
        dispatch(fetchObjects({object: "user", pagination}))
    }, [pagination])

    return (
        <div>
            <TableControls 
                header = {"Employees"} 
                setPagination={setPagination}
                object={"user"}
            />
            <Table 
                data = {users}
                headers={["First Name", "Last Name", "E-Mail", "Days Off Left", "Status"]}
                setModalData={setModalData}  
            />
            <Modal 
                modalHeader={`${modalData.action} EMPLOYEE`}
                modalContent={modalData.action === 'UPDATE' ? <UpdateUser entity={modalData.data}/> : <DeleteUser entity={modalData.data}/>}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )
}

export default Employees;








