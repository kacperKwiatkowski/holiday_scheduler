const initialState = [];

const NationalHolidaysReducer = (state = initialState, action) => {
    switch(action.type){
        case "FETCH_NATIONAL_HOLIDAYS":
            return action.payload;
        default:
            return state;
    }
}

export default NationalHolidaysReducer;