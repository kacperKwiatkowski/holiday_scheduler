import Axios from "axios";
import React, {Component, useEffect, useState} from "react";

class User extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [] 
        }
    }

    //{ id: 1, firstName: "sdc", lastName: "wsx", email: "efac", daysOffLeft: 22, roleType: "za"}

    componentDidMount() {
        Axios.get(`http://localhost:8080/user/read/all`)
          .then(res => {
            console.log(res)
            this.setState({users: res.data})
          });
    }

    renderTableBody () {
        return this.state.users.map((user, index) => {
            return (
                        <tr key={index}>
                            <td>
                                efcercf
                            </td>
                            <td>
                                {user.firstName}
                            </td>
                            <td>
                                {user.lastName}
                            </td>
                            <td>
                                {user.email}
                            </td>
                            <td>
                                {user.roleType}
                            </td>
                            <td>
                                {user.daysOffLeft}
                            </td>
                        </tr>
                )
            }
        )
    }

    render () {
        return (
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>
                                FIRST NAME
                            </th>

                            <th>
                                LAST NAME
                            </th>

                            <th>
                                E-MAIL
                            </th>

                            <th>
                                STATUS
                            </th>

                            <th>
                                DAYS OFF LEFT
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderTableBody()}
                    </tbody>
                </table>
            </div>
        )
    }

}

export default User



