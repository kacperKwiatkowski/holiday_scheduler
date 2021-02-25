import axios from "axios";
import React, {Component, useEffect, useState} from "react";
import "../styles/style.css"

const CalendarControls = ({header, setPagination}) => {

    const[usersCount, setUsersCount] = useState(0)
    const[state, setState] = useState({
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
        const response = await axios.get("http://localhost:8080/api/values/user/count")
        const { data } = await response;
        setUsersCount(data);
    }

    const renderPageNumControls = () => {

        var pageS = [];
        var i = 0;
        var max = usersCount / state.pageSize ;
        
        console.log(usersCount)

        if(usersCount!=0){
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

        if(usersCount!=0){
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
                >
                    <option>02</option>
                </select>
                <label className="controlsLabels">Year</label>
                <select
                    onChange={handleChange}
                    name="year" 
                    className="controlsElements"
                >
                    <option>2021</option>
                </select>
            </div>
        </form>
        )
} 

export default CalendarControls;