import { combineReducers } from 'redux'
import objectReducer from './objectsReducer'
import recordReducer from './recordReducer'
import loggedUserReducer from "./loggedUserReducer";

export default combineReducers({
    objectReducer,
    recordReducer,
    loggedUserReducer
})