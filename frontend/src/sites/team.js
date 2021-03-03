import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchEachTeam } from '../actions/fetchEachTeam'

import Headerbar from "../componenets/headerbar"
import Pagination from "../componenets/pagination"
import TeamSelector from "../selectors/teamSelector";
import TeamContent from "../componenets/teamContent";
import Modal from "../componenets/modal";

import UpdateTeam from "../forms/updateTeam"
import DeleteTeam from "../forms/deleteTeam";

const Teams = () => {

    const dispatch = useDispatch();
    const[pagination, setPagination] = useState({
        selectedTeam: 1
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    useEffect(() => {
        dispatch(fetchEachTeam())
    }, [pagination])

    console.log(pagination)
    
    return (
        <div>

            <Headerbar /> 
            <Pagination 
                header = {"Team"}
                pagination={pagination}
                setPagination={setPagination}
                teamSelector={<TeamSelector setPagination={setPagination} />}
                object={"team"}
            />
            {pagination.selectedTeam === null ? "Sorry" : <TeamContent pagination={pagination}/>}

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