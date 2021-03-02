import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchEachTeam } from '../actions/fetchEachTeam'

import Headerbar from "../componenets/headerbar"
import TableControls from "../componenets/tableControls"
import TeamSelector from "../componenets/teamSelector";
import Modal from "../componenets/modal";

import UpdateTeam from "../forms/updateTeam"
import DeleteTeam from "../forms/deleteTeam";

const Teams = () => {

    const dispatch = useDispatch();
    const teams = useSelector((state) => state.teamsReducer)
    const[pagination, setPagination] = useState({
    })
    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    useEffect(() => {
        dispatch(fetchEachTeam())
    }, [pagination])


    console.log(teams)
    
    return (
        <div>

            <Headerbar /> 
            <TableControls 
                header = {"Team"}
                setPagination={setPagination}
                object={"team"}
            />
            <TeamSelector teams={teams}/>
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