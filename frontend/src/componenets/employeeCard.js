import RemoveFromTeam from "../forms/removeFromTeam";

const EmployeeCard = ({member, setModalData}) => {

    return(

        <div className="card-wrapper">
            <img className="card-image" src={`http://localhost:8080/api/images/${member.id}/image/download`} alt={""}/> 
            <div className="card-content">
                <ul className="card-text">
                    <li className="card-text-elements">{member.firstName}</li>

                    <li className="card-text-elements">{member.lastName}</li>

                    <li className="card-text-elements">{member.email}</li>
                </ul>
                <div className="card-buttons-wrapper">
                    <button className="card-member-remove-button" onClick={() => setModalData({active: false, data: "REMOVE", action: <RemoveFromTeam entity={member}/>})}>REMOVE</button>
                </div>
            </div>
        </div>
    )
}

export default EmployeeCard;