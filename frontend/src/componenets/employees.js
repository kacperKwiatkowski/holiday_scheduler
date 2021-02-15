import React, {useState, useEffect} from "react";
import { connect, useDispatch, useSelector} from 'react-redux';
import { fetchUsers } from '../actions/userActions'
import Controls from "./controls"
import Modal from "./modal";
import Table from "./table.js";

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
    }, [pagination, modalData])

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

export default Employees;








