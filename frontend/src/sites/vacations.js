import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchObjects } from '../actions/fetchObjectsActions'
import Controls from "../componenets/controls"
import Modal from "../componenets/modal";
import Table from "../componenets/table";

const Vacations = () => {

    const dispatch = useDispatch();
    const vacations = useSelector((state) => state)
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

    
    console.log(vacations[0])
    return (
        <div>
            <Controls 
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
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )

}

export default Vacations