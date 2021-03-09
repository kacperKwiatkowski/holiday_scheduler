const initialState = [];

const PostReducer = (state = initialState, action) => {
    switch(action.type){
        case "POST_EMAIL":
            return action.payload;
        case "POST_VACATION_REQUEST":
            return action.payload;
        case "POST_NEW_USER":
            return action.payload;
        default:
            return state;
    }
}

export default PostReducer;