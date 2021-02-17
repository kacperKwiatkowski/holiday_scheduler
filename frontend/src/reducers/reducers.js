
const initialState = [];

const Reducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_CALENDAR":
            return action.payload;
        case "FETCH_USERS":
            return action.payload;
        case "UPDATE_OBJECT":
            return action.payload;
        case "DELETE_OBJECT":
            return action.payload;
        default:
            return state;
    }
}

export default Reducer;