import RemoveFromTeam from "../forms/removeFromTeam";

const EmployeeCard = ({member, setModalData}) => {
    
    console.log(member.roleType)

    return(

        <div className="card-wrapper">

            <img className="card-image" src={`http://localhost:8080/api/images/${member.id}/image/download`} alt={""}/> 
            <div className="card-content">

                <ul className="card-text">
                    {
                        member.roleType!=="EMPLOYEE" ? 
                        <li className="card-text-elements">LEADER</li> : null
                    }
                    <li className="card-text-elements">{member.firstName}</li>

                    <li className="card-text-elements">{member.lastName}</li>

                    <li className="card-text-elements">{member.email}</li>
                </ul>
                {
                    member.roleType==="EMPLOYEE" ? 
                    <div className="card-buttons-wrapper">
                        <button className="card-member-remove-button" onClick={() => setModalData({active: false, data: "REMOVE", action: <RemoveFromTeam entity={member}/>})}>REMOVE</button>
                    </div> : null
                }

            </div>
        </div>
    )
}

export default EmployeeCard;