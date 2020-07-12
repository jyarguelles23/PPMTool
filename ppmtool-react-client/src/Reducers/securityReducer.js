import { SET_CURRENT_USER } from "../Actions/types";
import { act } from "react-dom/test-utils";

const initialState = {
  user: {},
  //see if user is authentiated or valid token
  validToken: false,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        user: action.payload,
      };
    default:
      return state;
  }
}

const booleanActionPayload = (payload) => {
  return payload ? true : false;
};
