const initialState = [];

const ActionReducer = (state = initialState, action) => {
    switch(action.type){
        case "UPDATE_CREDENTIALS":
            return action.payload;
        default:
            return state;
    }
}

export default ActionReducer;