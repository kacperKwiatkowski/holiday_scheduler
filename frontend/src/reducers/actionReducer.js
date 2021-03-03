const initialState = [];

const ActionReducer = (state = initialState, action) => {
    switch(action.type){
        case "UPDATE_OBJECT":
            return action.payload;
        case "DELETE_OBJECT":
            return action.payload;
        case "REMOVE_OBJECT":
            return action.payload;
        default:
            return state;
    }
}

export default ActionReducer;