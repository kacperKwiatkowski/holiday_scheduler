import React, {useState, useEffect} from "react";
import { useDispatch, useSelector} from 'react-redux';
import SendEmail from "../forms/sendEmail";
import ChangeEmail from "../forms/changeEmail";
import ChangePassword from "../forms/changePassword";
import RequestVacation from "../forms/requestVacation";
import Dropzone from './dropzone';
import Modal from "../componenets/modal";

import { fetchLoggedUser } from '../actions/fetchLoggedUser'


const HeaderDropdown = ({dropDownStatus}) => {

    const dispatch = useDispatch();
    const loggedUser = useSelector((state) => state.loggedUserReducer)

    useEffect(() => {
        dispatch(fetchLoggedUser(localStorage.getItem('loggedUser')))
    }, [fetchLoggedUser])


    const [modalData, setModalData] = useState({active: true, data: "", action: ""});

    const renderModal = ({action}) => {
        console.log(action)

        switch (action) {
            case 'SEND_EMAIL':
                setModalData({active: !modalData.active, data: 'SEND_EMAIL', action: <SendEmail />})
                break;

            case 'CHANGE_EMAIL':
                
                setModalData({active: !modalData.active, data: 'CHANGE_EMAIL', action: <ChangeEmail />})
                break;

            case 'CHANGE_PASSWORD':
                
                setModalData({active: !modalData.active, data: 'CHANGE_PASSWORD', action: <ChangePassword />})
                break;

            case 'REQUEST_VACATION':
                
                setModalData({active: !modalData.active, data: 'CHANGE_PASSWORD', action: <RequestVacation />})
                break;
        
            default:
                break;
        }

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
                    <td>FIRST NAME: {loggedUser.firstName.toUpperCase()}</td>
                </tr>
                <tr>
                    <td>LAST NAME: {loggedUser.lastName.toUpperCase()}</td>
                </tr>
                <tr>
                    <td>E:MAIL: {loggedUser.email.toUpperCase()}</td>
                    </tr>
                <tr>
                    <td>ROLE: {loggedUser.roleType.toUpperCase()}</td>
                </tr>
                <tr>
                    <td>REMAINING DAYS OFF: {loggedUser.daysOffLeft}</td>
                </tr>
            </tbody>
        )
    }

    const renderPanel = () => {

        return(
            <tbody>
                <tr>
                    <td>
                        <button className="profile-dropDown-button" onClick={() => renderModal({action: "SEND_EMAIL"})}>SEND AN EMAIL</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button" onClick={() => renderModal({action: "CHANGE_EMAIL"})}>CHANGE EMAIL</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button" onClick={() => renderModal({action: "CHANGE_PASSWORD"})}>CHANGE PASSWORD</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="profile-dropDown-button" onClick={() => renderModal({action: "REQUEST_VACATION"})}>REQUEST VACATION</button>
                    </td>
                </tr>
            </tbody>
        )
    }

    return(
        <>
            <div className={dropDownStatus ? "profile-dropDown profile-dropDown-visable" : "profile-dropDown profile-dropDown-hidden"}>
                <table className="profile-dropDown-table">
                    {loggedUser.length === 0 ? null : renderProfile()}
                </table>
                <div className="profile-dropDown-service-form">
                    
                </div>
                <table className="profile-dropDown-buttons-wrapper">
                    {renderPanel()}
                </table>

                    
            </div>
                <Modal 
                    modalHeader={modalData.data.replace("_", " ")}
                    modalContent={modalData.action}
                    modalData={modalData}
                    setModalData={setModalData}  
                />
        </>

        
    )
}

export default HeaderDropdown;