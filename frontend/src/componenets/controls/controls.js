import React, {Component} from "react";
import "./controls.css";

class Controls extends Component{

    constructor(props){
        super(props)
        this.state = {
            numOfPages: 10,
            pageSize: 10
        }

    }

    render() {

        var pageN = [1,2,3,4,5];


        return (
        <div className="controlsWrapper">
            
            <ul className="controlsElements">
                Page: {this.renderPageNumControls()}
            </ul>
            <ul className="controlsElements">
                Size: {this.renderPageSizeControls()}
            </ul>
        </div>
        )
    }

    renderPageNumControls() {

        var pageS = [1,2,3,4,5];

        return( pageS.map((num) => {
            return (
                <li className="controlElement">  
                    <a className="controlLink">{num}</a>
                </li>
            )})
        )
    }

    renderPageSizeControls() {

        var pageS = [10, 15, 25];

        return( pageS.map((num) => {
            return (
                <li className="controlElement">  
                    <a className="controlLink">{num}</a>
                </li>
            )})
        )
    }
} 

export default Controls;