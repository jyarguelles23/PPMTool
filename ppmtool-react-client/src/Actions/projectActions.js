import axios from "axios";
import { GET_ERRORS } from "./types";
import { GET_PROJECTS, GET_PROJECT } from "./types";
export const createProject = (project, history) => async (dispatch) => {
  try {
    const rest = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  try {
    const rest = await axios.get("http://localhost:8080/api/project/all");
    //dispatch past the data we get from the sprinboot service
    dispatch({
      type: GET_PROJECTS,
      payload: rest.data,
    });
  } catch (error) {
    /* dispatch({
      type: GET_PROJECTS,
      payload: error.response.data,
    });*/
  }
};

export const getProject = (id, history) => async (dispatch) => {
  try {
    const rest = await axios.get(`http://localhost:8080/api/project/${id}/`);
    //const rest = await axios.get("http://localhost:8080/api/project/"+id);
    //dispatch past the data we get from the sprinboot service
    dispatch({
      type: GET_PROJECT,
      payload: rest.data,
    });
  } catch (error) {
    /* dispatch({
      type: GET_PROJECTS,
      payload: error.response.data,
    });*/
  }
};
