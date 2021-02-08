import React, {Component} from "react";
import "../../styles/style.css"

class Controls extends Component{

        state = {
            header: "",
            numOfPages: 10,
            pageSize: 10
        }

    render() {


        return (
        <div className="controlsWrapper">
            <div className="pageHeader">{this.props.header}</div>
            <div className="controlsElementsWrapper">
                <input className="filterBar" type="text" name="filter" placeholder="Filter results" ></input>
                <ul className="controlsElements">
                    Page: {this.renderPageNumControls()}
                </ul>
                <ul className="controlsElements">
                    Size: {this.renderPageSizeControls()}
                </ul>
            </div>
        </div>
        )
    }

    renderPageNumControls() {

        var pageS = [1,2,3,4,5];

        return( pageS.map((num, index) => {
            return (
                <li className="controlElement">  
                    <a className="controlLink">{num}</a>
                </li>
            )})
        )
    }

    renderPageSizeControls() {

        var pageS = [10, 15, 25];

        return( pageS.map((num, index) => {
            return (
                <li className="controlElement">  
                    <a className="controlLink">{num}</a>
                </li>
            )})
        )
    }
} 

export default Controls;