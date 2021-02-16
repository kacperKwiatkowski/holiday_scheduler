import { useState, useEffect } from "react";

const Form = ({action, entity, setEntity}) => {

    const[object, setObject] = useState(entity)
    useEffect(() => setObject(entity))

    const handleUpdateChange = (event) => {
        const value = event.target.value;
        setEntity({
            ...entity,
            [event.target.name]: value
        });
    }

    const updateUser = (entity) => {
        console.log(entity)
        return(
            Object.entries(entity).map(([key, value], index) => {

                if(key!=="id"){
                
                    return (
                        <li className="modalText">
                                <input 
                                    className="modalTextInput" 
                                    name={key}
                                    placeholder={value}
                                    defaultValue={value}
                                    onChange={event => (handleUpdateChange(event))}
                                />
                        </li>
                    )
                }
            })
        )
    }

    const deleteUser = (entity) => {
        return(
            Object.entries(entity).map(([key, value]) => {

                if(key!=="id"){

                    return (
                        <li className="modalText">
                            {value}
                        </li>
                    )
                }
            })
        )
    }

    switch(action) {
        case "UPDATE_USER":
            return updateUser(entity);
        case "DELETE_USER": 
            return deleteUser(entity);
        default:
            return <p></p>
    }


}

export default Form;