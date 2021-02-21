const initialState = [];

const RecordReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_CALENDAR":
            return action.payload;
        default:
            return state;
    }
}

export default RecordReducer;