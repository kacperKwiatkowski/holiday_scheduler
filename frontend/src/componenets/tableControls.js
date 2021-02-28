import axios from "axios";
import React, {useEffect, useState} from "react";
import "../styles/style.css"

const TableControls = ({header, setPagination, object}) => {

    const[objectCount, setobjectCount] = useState(0)
    const[state, setState] = useState({
        //FIXME pagination drops sortBy param here
        filter: "", pageNum: 1, pageSize: 5,  sortOrder: "ASC"
    })

    useEffect(() => getObjectCount(), [])

    function handleChange(event) {
        const value = event.target.value;

        if(event.target.name==='filter'){
            console.log("filter")
            setTimeout(setPagination({... state, 'filter': value}), 2000)

            setTimeout(setState({... state, 'filter': value}), 2000)
        }
        setPagination({
            ...state,
            [event.target.name]: value
        });
    }


    const getObjectCount = async () => {
        const response = await axios.get(`http://localhost:8080/api/values/${object}/count`)
        const { data } = await response;
        setobjectCount(data);
    }

    const renderPageNumControls = () => {

        var pageS = [];
        var i = 0;
        var max = objectCount / state.pageSize ;

        if(objectCount!=0){
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

        if(objectCount!=0){
            for(i; i <= 25; i=i+5){
                if(objectCount>i*(state.pageNum)){
                    pageS.push(i+5)
                }
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
                        <select 
                            name="pageNum" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            {renderPageNumControls()}
                        </select>
                </label>

                <label 
                    className="controlsLabels">
                        Size: 
                        <select 
                            name="pageSize" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            {renderPageSizeControls()}
                        </select> 
                </label>

                <label 
                    className="controlsLabels">
                        Order: 
                        <select 
                            name="sortOrder" 
                            className="controlsElements"
                            onChange={handleChange}
                        >
                            <option>ASC</option>
                            <option>DESC</option>
                        </select>
                </label>
            </div>
        </form>
        )
} 

export default TableControls;