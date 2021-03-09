import {useSelector} from 'react-redux';


const TeamSelector = ({pagination, setPagination}) => {

    const teams = useSelector((state) => state.teamsReducer)

    function handleChange(event) {
        const value = event.target.value;
        setPagination({
            ...pagination,
            [event.target.name]: value
        });
    }

    return(
        <select 
                name="selectedTeam" 
                className="controlsElements"
                onChange={handleChange}
        >
        <option disabled selected value> -- SELECT A TEAM -- </option>
                {
                    teams.map((team) => {return(<option value={team.id}>{team.name}</option>)})
                }
        </select>
    )
}
export default TeamSelector