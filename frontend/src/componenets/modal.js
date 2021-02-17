import React, {useState, useEffect} from "react";
import { useDispatch } from 'react-redux';
import { updateObject } from '../actions/updateObjectAction'
import { deleteObject } from '../actions/deleteObjectAction'
import Form from "./form";

const Modal = ({modalHeader, modalData, setModalData}) => {

    const dispatch = useDispatch();
    const [update, setUpdate] = useState(modalData.data)
    
    useEffect(() => {setUpdate(modalData.data)}, [modalData.data])

    const executeRequest = () => {
        if(modalData.action === 'UPDATE'){
            dispatch(updateObject({
                object: "user",
                data: update
            }))
            setModalData({active: true, data: "", task: ""})
        } else {
            dispatch(deleteObject({
                object: "user",
                id: modalData.data.id
            }))

            setModalData({active: true, data: "", task: ""})
        }

    }

    return(
        <div 
            className={modalData.active ? 'modalHiddenPosition modalBackground': 'modalVisablePosition modalBackground'} 
        >
            <form
                className="modalWrapper">
                <div className="modalHeader">
                    {modalHeader} 
                </div>

                <ul className="modalList">
                    <Form action={modalData.action + "_USER"} entity={update} setEntity={setUpdate}/>
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
        </div>
    )
}

export default Modal