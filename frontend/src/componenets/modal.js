import React, {useState, useEffect} from "react";
import deleteUser from "../controllers/delete"
import patchUser from "../controllers/patch"

const Modal = ({modalData, setModalData}) => {

    const [update, setUpdate] = useState(modalData.data)
    
    useEffect(() => {setUpdate(modalData.data)}, [modalData.data])

    console.log(update)

    const printObject = () => {
        if(modalData.action==="UPDATE"){
            return (updateForm())
        } else {
            return (deleteForm())
        }
    }

    const handleUpdateChange = (event) => {
        const value = event.target.value;
        setUpdate({
            ...update,
            [event.target.name]: value
        });
        console.log(update);
    }

    const updateForm = () => {

        return(
            Object.entries(modalData.data).map(([key, value], index) => {

                var a = value;

                if(key!=="id"){
                
                    return (
                        <li className="modalText">
                            <label> {key}: 
                                <input 
                                    className="modalTextInput" 
                                    name={key}
                                    placeholder={value}
                                    defaultValue={value}
                                    onChange={event => (handleUpdateChange(event))}
                                />
                            </label>
                        </li>
                    )
                }
            })
        )
    }

    const deleteForm = () => {
        return(
            Object.entries(modalData.data).map(([key, value]) => {

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

    const executeRequest = () => {
        if(modalData.action === 'UPDATE'){
            console.log("PATCH " + update.firstName)
            patchUser(
                {
                    object: "user",
                    data: update
                })
            setModalData({active: true, data: "", task: ""})
        } else {
            deleteUser(
                {
                    object: "user",
                    id: modalData.data.id
                })

            setModalData({active: true, data: "", task: ""})
        }

    }

    return(
            <form
                className="modalWrapper">
                <div className="modalHeader">
                 {modalData.action} EMPLOYEE'S ACCOUNT
                </div>
                <ul className="modalList">
                    {printObject()}
                </ul>
                <div className="modalButtonsWrapper">
                    <button 
                        type="button"
                        className="modalButton"
                        onClick={() => setModalData({active: true, data: "", task: ""})}
                    >
                        DISMISS
                    </button>
                    <button 
                        type="button"
                        className={modalData.action === "UPDATE" ? `modalButton modalUpdateButton` : `modalButton modalDeleteButton`}
                        onClick={() => executeRequest(modalData.data.userId)} 
                    >
                        {modalData.action}
                    </button>
                </div>
            </form>
    )
}

export default Modal