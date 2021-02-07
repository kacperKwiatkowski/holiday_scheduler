var Axios = require("axios");

export const jwtToken = localStorage.getItem("authorization");

Axios.interceptors.request.use(
    function(config) {
        console.log("THIS IS INTERCEPTOR")
        console.log(jwtToken)
      if (jwtToken) {
        config.headers["authorization"] = jwtToken;
      
        //config.headers["authorization"] = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZHl0YUBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9FTVBMT1lFRSJ9LHsiYXV0aG9yaXR5IjoiYWRtaW46cmVhZCJ9LHsiYXV0aG9yaXR5IjoiZW1wbG95ZWU6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiaHI6cmVhZCJ9LHsiYXV0aG9yaXR5Ijoic2VsZjplZGl0In0seyJhdXRob3JpdHkiOiJ0ZWFtOnJlYWQifSx7ImF1dGhvcml0eSI6InRlYW1MZWFkZXI6cmVhZCJ9LHsiYXV0aG9yaXR5IjoidmFjYXRpb246Y3JlYXRlIn0seyJhdXRob3JpdHkiOiJ2YWNhdGlvbjpub3RBY2NlcHRlZEVkaXQifSx7ImF1dGhvcml0eSI6InZhY2F0aW9uOnJlYWQifV0sImlhdCI6MTYxMjUzNjIyMCwiZXhwIjoxNjEzNjg5MjAwfQ.d_SngE5zpspmXDjTxo6HD9JHIaJ_T7_2MBtD3inkib8";
      }
      return config;
    },
    function(err) {
      return Promise.reject(err);
    }
  );