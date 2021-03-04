import axios from "axios";
import React, {useEffect, useState} from "react";
import "../styles/style.css"

const CalendarControls = ({header, setPagination}) => {

    const[usersCount, setUsersCount] = useState(0)
    const[state, setState] = useState({
        filter: "",
        pageNum: 1,
        pageSize: 5,
        sortBy: "lastName",
        sortOrder: "ASC",
        month: ("0" + (new Date().getMonth() + 1)).slice(-2),
        year: new Date().getFullYear()
    })

    useEffect(() => getUsersCount(), []);

    function handleChange(event) {
        const value = event.target.value;
        setPagination({
            ...state,
            [event.target.name]: value
        });
        setState({
            ...state,
            [event.target.name]: value
        });
    }


    const getUsersCount = async () => {
        const response = await axios.get(`http://localhost:8080/api/values/user/count`)
        const { data } = await response;
        setUsersCount(data);
    }

    const renderPageNumControls = () => {

        var pageS = [];
        var i = 0;
        var max = usersCount / state.pageSize ;

        if(usersCount!==0){
            for(i = 0; i <= max; i++){
                pageS.push(i+1)
            }
        }

        return( pageS.map((num, index) => {
            return (
                <option 
                    className="controlElement"
                    key={index}
                >  
                    {num}
                </option>
            )})
        )
    }

    const renderPageSizeControls = () => {
        var pageS = [];
        var i = 0;

        if(usersCount!==0){
            for(i; i <= 25; i=i+5){
                if(usersCount>i*(state.pageNum)){
                    pageS.push(i+5)
                }
            }
        }

        return( pageS.map((num, index) => {
            return (
                <option className="controlElement">  
                    {num}
                </option>
            )})
        )
    }

    const months = ['01','02','03','04','05','06','07','08','09','10','11','12'] 

    const years = () => {

        var year = new Date().getFullYear()

        var years = [];

        years.push(year - 1)
        years.push(year)
        years.push(year + 1)

        return years;
    }


    return (
        <form className="controlsWrapper">
            <div className="pageHeader">{header}</div>
            <div className="controlsElementsWrapper">
                <input 
                    type="text" 
                    name="filter" 
                    className="filterBar" 
                    placeholder="Filter results" 
                    onChange={handleChange}
                />

                <label 
                    className="controlsLabels">
                        Page:
                </label>
                        <select 
                            name="pageNum" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            {renderPageNumControls()}
                        </select>

                <label 
                    className="controlsLabels">
                        Size: 
                </label>
                        <select 
                            name="pageSize" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            {renderPageSizeControls()}
                        </select> 

                <label 
                    className="controlsLabels">
                        Order: 
                </label>
                        <select 
                            name="sortOrder" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            <option>ASC</option>
                            <option>DESC</option>
                        </select>
                        
                <label className="controlsLabels">Month</label>
                <select
                    name="month" 
                    className="controlsElements"
                    onChange={handleChange}
                    value={state.month}
                >
                    {
                        months.map(month => {
                            return(
                                <option>{month}</option>
                            )
                        })
                    }
                </select>
                <label className="controlsLabels">Year</label>
                <select
                    onChange={handleChange}
                    name="year" 
                    className="controlsElements"
                    value={state.year}
                >
                    {
                        years().map(year => {
                            return(
                                <option>{year}</option>
                            )
                        })
                    }
                </select>
            </div>
        </form>
        )
} 

export default CalendarControls;