const initialState = [];

const LoggedUserImageReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_LOGGED_USER_IMAGE":
            return action.payload;
        default:
            return state;
    }
}

export default LoggedUserImageReducer;