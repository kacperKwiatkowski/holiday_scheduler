const initialState = [];

const TeamsReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_EACH_TEAM":
            return action.payload;
        default:
            return state;
    }
}

export default TeamsReducer;