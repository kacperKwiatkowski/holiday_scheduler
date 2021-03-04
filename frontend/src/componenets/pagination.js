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
                    } else return null

                })
                
            }
            </div>


        </form>
        )

}

export default  Pagination