import Axios from 'axios'
import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchTeam } from '../actions/fetchTeam'
import interceptor from "../interceptor/interceptor"
import Card from "./employeeCard";

const TeamContent = ({pagination, setModalData}) => { 

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchTeam(pagination.selectedTeam))
        getMembers()
    }, [pagination.selectedTeam])

    const [team, setTeam] = useState()

    const getMembers = () => {

        Axios.get(`http://localhost:8080/api/team/read/squad/${pagination.selectedTeam}`)
        .then(res => { setTeam(res.data)})
        .catch(err => console.log(err))
    }

    console.log(team)

    const renderTeam = () => {


        return(
            team.map((member) => {
                return(
                    <Card member={member} setModalData={setModalData}/>
                )
            })
        )
    }

    return(
        <table className="tables">
            <thead>
                <div className="team-header">
                </div>
            </thead>
            <tbody>
                    <div className="cards-wrapper">
                            {team === (null || undefined) ? "SORRY" : renderTeam()}
                    </div>



            </tbody>

        </table>

    )
}

export default TeamContent