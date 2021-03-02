const initialState = [];

const TeamReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_TEAM":
            return action.payload;
        default:
            return state;
    }
}

export default TeamReducer;