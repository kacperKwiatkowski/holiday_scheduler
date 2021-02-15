
import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import userReducer from "../reducers/userReducers";
import {composeWithDevTools} from "redux-devtools-extension";

const store = createStore(
    userReducer, 
    composeWithDevTools(applyMiddleware(thunk))
    );

export default store;