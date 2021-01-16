

export const calendar = () => {

    var getDaysInMonth = function(month,year) {
        return new Date(year, month, 0).getDate();
    }

    var getCurrentMonth = function () {
        var date = new Date();
        return date.getMonth()
    }

    var getCurrentYear = function () {
        var date = new Date();
        return date.getFullYear()
    }

    const row

    return(
        <table className="">
            <thead>
            <tr>
                
                <th>
                    
                </th>
                <th>
   
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                </td>
                <td>
                </td>
            </tr>
            </tbody>
            <tfoot>
            </tfoot>
        </table>
    )
}

export default calendar