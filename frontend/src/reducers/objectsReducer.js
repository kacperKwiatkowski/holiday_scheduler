
const initialState = []

const ObjectReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_OBJECTS":
            return action.payload;
        default:
            return state;
    }
}

export default ObjectReducer;