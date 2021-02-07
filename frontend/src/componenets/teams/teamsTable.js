import Axios from "axios";
import React, {Component} from "react";
import "../../styles/style.css"

class Teams extends Component {

    constructor(props) {
        super(props);
        this.state = {
            teams: [],
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
    
        Axios.get(`http://localhost:8080/api/team/page?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASC`, {
        
            credentials: 'include',
            headers: {
                'Content-Type': 'multipart/form-data',
            }
        })
            .then(res => {
            console.log(res)
            this.setState({teams: res.data})
        });
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

    renderTableHead () {
        return(
            <tr>
                <th>
                    TEAM NAME
                </th>

                <th>
                    TEAM LEADER
                </th>
            </tr>
        )
    }

    renderTableBody () {
        return this.state.teams.map((team, index) => {
            return (
                        <tr>
                            <td>
                                {team.name}
                            </td>
                            <td>
                                {team.teamLeaderId}
                            </td>
                        
                        </tr>
                )
            }
        )
    }


}

export default Teams