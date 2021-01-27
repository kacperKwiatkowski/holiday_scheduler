import Axios from "axios";
import React, {Component} from "react";
import "./employeesTable.css"

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

    //{ id: 1, firstName: "sdc", lastName: "wsx", email: "efac", daysOffLeft: 22, roleType: "za"}

    componentDidMount() {
        Axios.get(`http://localhost:8080/user/page?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASC`)
          .then(res => {
            console.log(res)
            this.setState({users: res.data})
          });
    }

    renderTableHead () {
        return(
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
        )
    }

    renderTableBody () {
        return this.state.users.map((user, index) => {
            return (
                        <tr key={index}>
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
                <table className="employeesTable">
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



