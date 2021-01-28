import Axios from "axios";
import React, {Component} from "react";
import "./teamsTable.css"

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
        Axios.get(`http://localhost:8080/team/page?pageNo=0&pageSize=10&sortBy=id&sortOrder=ASC`)
          .then(res => {
            console.log(res)
            this.setState({teams: res.data})
          });
    }


    render () {
        return (
            <div>
                <table className="teamsTable">
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
        return this.state.teams.map((team) => {
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