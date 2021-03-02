import Axios from 'axios'
import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import { fetchTeam } from '../actions/fetchTeam'
import interceptor from "../interceptor/interceptor"
import Card from "./employeeCard";

const TeamSelector = ({teams}) => { 


    const[selectedTeam, setSelectedTeam] = useState({
        id: 1
    })
    const[teamMembers, setTeamMembers] = useState({
    })

    const dispatch = useDispatch();
    const team = useSelector((state) => state.teamReducer)

    useEffect(() => {
        alert("EFFECT")
        dispatch(fetchTeam(selectedTeam.id))
        setTeamMembers(getMembers(selectedTeam.id))
    }, [selectedTeam])

    function handleChange(event) {
        const value = event.target.value;
        setSelectedTeam({
            ...selectedTeam,
            [event.target.name]: value
        });
    }

    const getMembers = (id) => {

        Axios.get(`http://localhost:8080/api/team/read/squad/${id}`)
        .then(res => { setTeamMembers(res.data)})
        .catch(err => console.log(err))
    }


    return(
        <table className="tables">
            <thead>
                <select 
                        name="id" 
                        className="controlsElements"
                        onChange={handleChange}
                    >
                        {
                            teams.map((team) => {return(<option value={team.id}>{team.id}</option>)})
                        }
                </select>
            </thead>
            <tbody>
                <div className="cards-wrapper">
                    {
                        teamMembers.map((member) => {
                            return(
                                <Card member={member} />
                            )
                        })
                    }
                </div>


            </tbody>

        </table>

    )
}

export default TeamSelector