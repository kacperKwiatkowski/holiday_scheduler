import { combineReducers } from 'redux'
import objectReducer from './objectsReducer'
import recordReducer from './recordReducer'

export default combineReducers({
    objectReducer,
    recordReducer
})