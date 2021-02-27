import { combineReducers } from 'redux'
import objectReducer from './objectsReducer'
import actionReducer from './actionReducer'
import recordReducer from './recordReducer'
import loggedUserReducer from "./loggedUserReducer";
import nationalHolidaysReducer from "./nationalHolidaysReducer";

export default combineReducers({
    objectReducer,
    actionReducer,
    recordReducer,
    loggedUserReducer,
    nationalHolidaysReducer
})