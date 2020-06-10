import { combineReducers } from "redux";
import errorReducer from "./errorReducer";

export default combineReducers({
  //all the reducers we will creating

  errors: errorReducer,
});
