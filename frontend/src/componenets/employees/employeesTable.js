import Axios from "axios";
import React, {Component} from "react";
import "../../styles/style.css"
class User extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            pagination: this.initialPagination
        }
    }

    initialPagination = {
        pageNo: 0,
        pageSize: 10,
        sortBy: 'id',
        sortOrder: 'ASC'
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/api/user/page?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASC`)
          .then(res => {
            console.log(res)
            this.setState({users: res.data})
          });
    }

    renderTableHead () {
        return(
            <tr>
                <th>
                    First Name
                </th>

                <th>
                    Last Name
                </th>

                <th>
                    E-Mail
                </th>

                <th>
                    Status
                </th>

                <th>
                    Days off left
                </th>

                <th>
                    Action
                </th>
            </tr>
        )
    }

    renderTableBody () {
        return this.state.users.map((user) => {
            return (
                        <tr>
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
                            <td className="actionButtonsWrapper">
                                <button className="editButton"/>
                                <button className="deleteButton"/>
                            </td>
                        </tr>
                )
            }
        )
    }

    render () {
        return (
            <div>
                <table className="tables">
                    <thead>
                        {this.renderTableHead()}
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



