import { combineReducers } from 'redux'
import objectReducer from './objectsReducer'
import actionReducer from './actionReducer'
import recordReducer from './recordReducer'
import loggedUserReducer from "./loggedUserReducer";
import nationalHolidaysReducer from "./nationalHolidaysReducer";
import teamsReducer from "./teamsReducer"
import teamReducer from "./teamReducer"
import credentialsReducer from "./credentialsReducer"
import postReducer from "./postReducer"

export default combineReducers({
    objectReducer,
    actionReducer,
    recordReducer,
    loggedUserReducer,
    nationalHolidaysReducer,
    teamsReducer,
    teamReducer,
    credentialsReducer,
    postReducer
})