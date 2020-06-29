import axios from "axios";
import { GET_ERRORS } from "./types";
import { GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const rest = await axios.post("/api/project", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  try {
    const rest = await axios.get("/api/project/all");
    //dispatch past the data we get from the sprinboot service
    dispatch({
      type: GET_PROJECTS,
      payload: rest.data,
    });
  } catch (error) {}
};

export const getProject = (id, history) => async (dispatch) => {
  try {
    const rest = await axios.get(`/api/project/${id}/`);
    //const rest = await axios.get("http://localhost:8080/api/project/"+id);
    //dispatch past the data we get from the sprinboot service
    dispatch({
      type: GET_PROJECT,
      payload: rest.data,
    });
  } catch (error) {
    history.push("/dashboard");
    /* dispatch({
      type: GET_PROJECTS,
      payload: error.response.data,
    });*/
  }
};

export const deleteProject = (id, history) => async (dispatch) => {
  try {
    if (window.confirm("Are yousure ? this will delete the project")) {
      const rest = await axios.delete(`/api/project/${id}/`);
      //const rest = await axios.get("http://localhost:8080/api/project/"+id);
      //dispatch past the data we get from the sprinboot service
      dispatch({
        type: DELETE_PROJECT,
        payload: id,
      });
    }
  } catch (error) {
    /* dispatch({
      type: GET_PROJECTS,
      payload: error.response.data,
    });*/
  }
};
