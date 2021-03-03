

const Pagination = (props) => {

    return (
        <form className="controlsWrapper">
            <div className="pageHeader">{props.header}</div>

            <div className="controlsElementsWrapper">
            {

                Object.entries(props).map(([key, value]) => {
                    
                    if(value !== null && key.toLowerCase().includes('selector')){
                        return (
                            <>
                            <label 
                                className="controlsLabels">
                                    Select team:
                            </label>
                                {value}
                            </>
                        )  
                    } 

                })
                
            }
            </div>


        </form>
        )

}

export default  Pagination

            {/* <div className="controlsElementsWrapper">
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
            </div> */}