import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import getProjectReducer from "./getProjectReducer";
import backlogReducer from "./backLogReducer";
import securityReducer from "./securityReducer";
export default combineReducers({
  //all the reducers we will creating

  errors: errorReducer,
  project: getProjectReducer,
  backlog: backlogReducer,
  security: securityReducer,
});
