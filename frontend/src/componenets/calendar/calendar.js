

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
        <h1>calendar</h1>
    )
}

export default calendar