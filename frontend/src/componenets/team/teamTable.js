import Axios from "axios";
import React, {Component} from "react";
import "../../styles/style.css"

class Team extends Component {

    constructor(props) {
        super(props);
        this.state = {
            team: [],
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
        Axios.get(`http://localhost:8080/api/team/read/1`)
          .then(res => {
            console.log(res)
            this.setState({team: res.data})
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
        return (
                        <tr>
                            <td>
                                {this.state.team.name}
                            </td>

                            <td>
                                {this.state.team.teamLeaderId}
                            </td>
                        
                        </tr>
        )
    }


}

export default Team