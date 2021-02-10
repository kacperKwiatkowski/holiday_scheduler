import React, {useState, useEffect} from "react";

import "../styles/style.css"

const Modal = ({modalData, setModalData}) => {

    console.log(modalData.data)

    console.log(modalData.data.firstName)

    const printObject = () => {
        return(
            Object.entries(modalData.data).map(([key, value]) => {
                return (
                    <li className="modalText">
                        {value}
                    </li>
                )
            })
        )
    }

    return(
            <div className="modalWrapper">
                <div className="modalHeader">
                    EMPLOYEE ACCOUNT {modalData.action}
                </div>
                <ul className="modalList">
                    {printObject()}
                </ul>
                <div className="modalButtonsWrapper">
                    <button 
                        className="modalButton "
                        onClick={() => setModalData({active: true, data: "", task: ""})}
                    >
                        DISMISS
                    </button>
                    <button 
                        className={modalData.action === "UPDATE" ? `modalButton modalUpdateButton` : `modalButton modalDeleteButton`}
                        onClick={() => setModalData({active: true, data: "", task: ""})}
                    >
                        {modalData.action}
                    </button>
                </div>
            </div>
    )
}

export default Modal