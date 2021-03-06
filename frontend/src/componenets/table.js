import { FaPen, FaTimes } from "react-icons/fa";

const Table = (props) => {

    const renderTable = () => {
        return (
            <table className="tables">
                <thead>
                    {renderTableHead()}
                </thead>
                <tbody>
                    {renderTableBody()}
                </tbody>
            </table>
        )
    }

    const renderTableHead = () => {
        return(
            <tr>
                {
                    props.headers.map((header) => {
                        return(
                            <th>
                                {header}
                            </th>
                        )
                    })
                }
                <th>
                    Action
                </th>
            </tr>
        )
    }

    const renderTableBody = () => {
        return props.data.map((object) => {
               return renderTableBodyRows(object);
            }
        )
    }

    const renderTableBodyRows = (object) => {
        return (
        <tr>
            {Object.entries(object).map(([key, value]) => {
                if(value !== null && !key.toLowerCase().includes('id')){
                    return (
                        <td key={key}>
                        {value.toString()}
                        </td>
                    )  
                } else return null
            })}

            <td className="actionButtonsWrapper">
                <button className="editButton" onClick={() => props.setModalData({active: false, data: object, action: "UPDATE"})}><FaPen className="dropActiveButtonIcon"/></button>
                <button className="deleteButton" onClick={() => props.setModalData({active: false, data: object, action: "DELETE"})}><FaTimes className="deleteButtonIcon"/></button>
            </td>
        </tr>
        )
    }

    return renderTable();

}

export default Table