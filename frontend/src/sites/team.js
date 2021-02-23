import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchObjects } from '../actions/fetchObjectsActions'

import Headerbar from "../componenets/headerbar"
import TableControls from "../componenets/tableControls"
import Modal from "../componenets/modal";
import Table from "../componenets/table";

const Team = () => {

    const dispatch = useDispatch();
    const team = useSelector((state) => state.objectReducer)
    const[pagination, setPagination] = useState({
        pageNum: 1,
        pageSize: 5,
        sortBy: 'name',
        sortOrder: 'ASC',
        filter: ''
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});


    useEffect(() => {
        dispatch(fetchObjects({object: 'team', pagination}))
    }, [pagination])

    
    return (
        <div>

            <Headerbar /> 
            <TableControls 
                header = {"Team"} 
                setPagination={setPagination}
            />
            <Table 
                data = {team}
                headers={["Team's name", "Team leader's email", "Team leader's first name", "Team leader's last name"]}
                setModalData={setModalData}  
            />
            <Modal 
                modalHeader={`${modalData.action} TEAM`}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )

}

export default Team