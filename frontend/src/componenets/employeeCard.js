const EmployeeCard = ({member}) => {

    return(

        <div className="card-wrapper">
            <img className="card-image" src={`http://localhost:8080/api/images/${member.id}/image/download`} alt={""}/> 
            <ul className="card-text">
                <li>{member.firstName}</li>

                <li>{member.lastName}</li>

                <li>{member.email}</li>
            </ul>
        </div>
    )
}

export default EmployeeCard;