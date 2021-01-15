

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

    return(
        <table className="">
            <thead>
            <tr>
                <th scope="col" className=""/>
                <th>
                    <button type="button" className="" >test</button>
                </th>
                <th>
                    <button type="button" className="">test</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <button type="button" className="">test</button>
                </td>
                <td>
                    <td>
                        <button type="button" className="">test</button>
                    </td>
                </td>
            </tr>
            </tbody>
            <tfoot>
            </tfoot>
        </table>
    )
}

export default calendar