import Users from "./employeesTable"

export const employees = () => {

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
        <div>

            <h1>employees</h1>
            <Users />
            <h1>employees</h1>
        </div>
    )
}

export default employees