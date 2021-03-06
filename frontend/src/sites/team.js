import React, {useState, useEffect} from "react";
import { useDispatch} from 'react-redux';
import { fetchEachTeam } from '../actions/fetchEachTeam'

import Headerbar from "../componenets/headerbar"
import Pagination from "../componenets/pagination"
import TeamSelector from "../selectors/teamSelector";
import TeamContent from "../componenets/teamContent";
import Modal from "../componenets/modal";

const Teams = () => {

    const dispatch = useDispatch();
    const[pagination, setPagination] = useState({
        selectedTeam: null
    })

    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    useEffect(() => {
        dispatch(fetchEachTeam())
    }, [pagination])
    
    return (
        <div  className={"tables"}>
            <Pagination 
                header = {"Team"}
                pagination={pagination}
                setPagination={setPagination}
                teamSelector={<TeamSelector setPagination={setPagination} />}
                object={"team"}
            />
            {pagination.selectedTeam === null ? <div className={"no-team-chosen"}>SELECT A TEAM</div>: 
                <TeamContent pagination={pagination} setModalData={setModalData}  />}

            <Modal 
                modalHeader={`REMOVE FROM TEAM`}
                modalContent={modalData.action}
                modalData={modalData}
                setModalData={setModalData}  
            />
        </div>
    )

}

export default Teams