import React, {useState} from "react";
import SendEmail from "../forms/sendEmail";
import Dropzone from './dropzone';
import Modal from "../componenets/modal";


const HeaderDropdown = ({dropDownStatus, loggedUser}) => {

    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    const renderModal = () => {
        setModalData({active: !modalData.active, data: 'SEND_EMAIL', action: <SendEmail />})

    }

    const renderProfile = () => {
        return(
            <tbody>
                <tr>
                    <td rowspan="6">
                        <Dropzone 
                            {...loggedUser}  
                        />

                    </td>
                    <td>FIRST NAME: {loggedUser.firstName}</td>
                </tr>
                <tr>
                    <td>LAST NAME: {loggedUser.lastName}</td>
                </tr>
                <tr>
                    <td>E:MAIL: {loggedUser.email}</td>
                    </tr>
                <tr>
                    <td>ROLE: {loggedUser.roleType}</td>
                </tr>
                <tr>
                    <td>REMAINING DAYS OFF: {loggedUser.daysOffLeft}</td>
                    </tr>
                <tr>
                    <td>TEAM:  </td>
                </tr>
            </tbody>
        )
    }

    const renderPanel = () => {
        return(
            <tbody>
                <tr>
                    <td>
                        <button className="profile-dropDown-button" onClick={() => renderModal()}>SEND AN EMAIL</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button">CHANGE EMAIL</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button">CHANGE PASSWORD</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button">REQUEST VACATION</button>
                    </td>
                </tr>
            </tbody>
        )
    }

    return(
        <>
            <div className={dropDownStatus ? "profile-dropDown profile-dropDown-visable" : "profile-dropDown profile-dropDown-hidden"}>
                <table className="profile-dropDown-table">
                    {renderProfile()}
                </table>
                <div className="profile-dropDown-service-form">
                    
                </div>
                <table className="profile-dropDown-buttons-wrapper">
                    {renderPanel()}
                </table>

                    
            </div>
                <Modal 
                    modalHeader={`SEND AN EMAIL`}
                    modalContent={modalData.action}
                    modalData={modalData}
                    setModalData={setModalData}  
                />
        </>

        
    )
}

export default HeaderDropdown;