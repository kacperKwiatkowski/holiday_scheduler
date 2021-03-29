import {applyMiddleware, createStore} from "redux";
import thunk from "redux-thunk";
import Reducer from "../reducers/rootReducer";
import {composeWithDevTools} from "redux-devtools-extension";

const store = createStore(
    Reducer, 
    composeWithDevTools(applyMiddleware(thunk))
    );

export default store;