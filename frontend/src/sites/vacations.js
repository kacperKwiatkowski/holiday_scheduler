import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchObjects } from '../actions/fetchObjectsActions'

import Headerbar from "../componenets/headerbar"
import TableControls from "../componenets/tableControls"
import Modal from "../componenets/modal";
import UpdateVacation from "../forms/updateVacation";
import DeleteVacation from "../forms/deleteVacation";
import Table from "../componenets/table";

const Vacations = () => {

    const dispatch = useDispatch();
    const vacations = useSelector((state) => state.objectReducer)
    const[pagination, setPagination] = useState({
        pageNum: 1,
        pageSize: 5,
        sortBy: 'firstDay',
        sortOrder: 'ASC',
        filter: ''
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});


    useEffect(() => {
        dispatch(fetchObjects({object: 'vacation', pagination}))
    }, [pagination])

    return (
        <div>

            <Headerbar /> 
            <TableControls 
                header = {"Vacations"} 
                setPagination={setPagination}
            />
            <Table 
                data = {vacations}
                headers={["First name", "Last name", "E-mail", "First day", "Last day", "Leave type", "Accepted"]}
                setModalData={setModalData}  
            />
            <Modal 
                modalHeader={`${modalData.action} VACATION REQUEST`}
                modalContent={modalData.action === 'UPDATE' ? <UpdateVacation entity={modalData.data}/> : <DeleteVacation entity={modalData.data}/>}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )

}

export default Vacations