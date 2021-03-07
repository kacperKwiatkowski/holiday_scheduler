var Axios = require("axios");

import store from '../store/store';

export const jwtToken = localStorage.getItem("authorization");

Axios.interceptors.request.use(
    function(config) {
      if (jwtToken) {
        config.headers["admin"] = store.getState().loggedUserReducer.id;
      }
      return config;
    },
    function(err) {
      return Promise.reject(err);
    }
  );