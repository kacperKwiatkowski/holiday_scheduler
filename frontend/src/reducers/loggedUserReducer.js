const initialState = [];

const LoggedUserReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_LOGGED_USER":
            return action.payload;
        default:
            return state;
    }
}

export default LoggedUserReducer;