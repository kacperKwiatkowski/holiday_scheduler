
const initialState = [];

const ObjectReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_OBJECTS":
            return action.payload;
        case "UPDATE_OBJECT":
            return action.payload;
        case "DELETE_OBJECT":
            return action.payload;
        default:
            return state;
    }
}

export default ObjectReducer;