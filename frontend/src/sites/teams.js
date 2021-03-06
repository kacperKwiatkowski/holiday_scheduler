import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchObjects } from '../actions/fetchObjectsActions'

import Headerbar from "../componenets/headerbar"
import TableControls from "../componenets/tableControls"
import Modal from "../componenets/modal";

import UpdateTeam from "../forms/updateTeam"
import DeleteTeam from "../forms/deleteTeam";
import Table from "../componenets/table";

const Teams = () => {

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
            <TableControls 
                header = {"Teams"}
                setPagination={setPagination}
                object={"teams"}
            />
            <Table 
                data = {team}
                headers={["Teams's name", "Teams leader's email", "Teams leader's first name", "Teams leader's last name"]}
                setModalData={setModalData}  
            />
            <Modal 
                modalHeader={`${modalData.action} TEAM`}
                modalContent={modalData.action === 'UPDATE' ? <UpdateTeam entity={modalData.data}/> : <DeleteTeam entity={modalData.data}/>}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )

}

export default Teams