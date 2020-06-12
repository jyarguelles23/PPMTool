import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import getProjectReducer from "./getProjectReducer";
export default combineReducers({
  //all the reducers we will creating

  errors: errorReducer,
  project: getProjectReducer,
});
