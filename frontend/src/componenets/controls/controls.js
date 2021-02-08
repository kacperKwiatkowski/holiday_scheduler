import React, {Component, useState} from "react";
import "../../styles/style.css"

const Controls = ({header}) => {

    const[viewSettings, setViewSettings] = useState({pageSize:10, pageNum: 0, pageOrder: "ASC"})

    const renderPageNumControls = () => {

        var pageS = [1,2,3,4,5];

        return( pageS.map((num, index) => {
            return (
                <option className="controlElement">  
                    {num}
                </option>
            )})
        )
    }

    const renderPageSizeControls = () => {

        var pageS = [10, 15, 25];

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
                <input className="filterBar" type="text" name="filter" placeholder="Filter results" ></input>
                <label className="controlsLabels">Page:</label>
                <select className="controlsElements">
                        {renderPageNumControls()}
                </select>
                <label className="controlsLabels">Size: </label>
    
                <select className="controlsElements">
                        {renderPageSizeControls()}
                </select>
                <label className="controlsLabels">Order: </label>
                <select className="controlsElements">
                        <option>Asc</option>
                        <option>Desc</option>
                </select>
            </div>
        </form>
        )
} 

export default Controls;