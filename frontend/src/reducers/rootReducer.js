import { combineReducers } from 'redux'
import objectReducer from './objectsReducer'
import actionReducer from './actionReducer'
import recordReducer from './recordReducer'
import loggedUserReducer from "./loggedUserReducer";

export default combineReducers({
    objectReducer,
    actionReducer,
    recordReducer,
    loggedUserReducer
})