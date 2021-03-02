
import Dropzone from './dropzone';

const HeaderDropdown = ({dropDownStatus, loggedUser}) => {

    return(
        <div className={dropDownStatus ? "profile-dropDown profile-dropDown-visable" : "profile-dropDown profile-dropDown-hidden"}>
                    <table className="profile-dropDown-table">
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
                    </table>
                    <table className="profile-dropDown-buttons-wrapper">
                        <tbody>
                            <tr>
                                <td>
                                    <button className="profile-dropDown-button">SEND AN EMAIL</button>
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
                    </table>
                
        </div>
    )
}

export default HeaderDropdown;