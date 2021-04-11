import axios from "axios";
import {
  GET_ERRORS,
  GET_PROJECT_TASK,
  GET_BACKLOG,
  GET_PROJECTS,
  DELETE_PROJECT_TASK,
} from "./type";

export const addProjectTask = (backlog_id, project_task, history) => async (
  dispatch
) => {
  try {
    await axios.post(
      `https://projectmanagement-amar.herokuapp.com/api/backlog/${backlog_id}`,
      project_task
    );
    history.push(`projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
export const getBacklog = (backlog_id) => async (dispatch) => {
  try {
    const res = await axios.get(
      `https://projectmanagement-amar.herokuapp.com/api/backlog/${backlog_id}`
    );
    dispatch({
      type: GET_BACKLOG,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
export const getProjectTask = (backlog_id, pt_id, history) => async (
  dispatch
) => {
  try {
    const res = await axios.get(
      `https://projectmanagement-amar.herokuapp.com/api/backlog/${backlog_id}/${pt_id}`
    );
    dispatch({
      type: GET_PROJECT_TASK,
      payload: res.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};
export const updateProjectTask = (
  backlog_id,
  pt_id,
  project_task,
  history
) => async (dispatch) => {
  try {
    await axios.patch(
      `https://projectmanagement-amar.herokuapp.com/api/backlog/${backlog_id}/${pt_id}`,
      project_task
    );
    history.push(`/projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const deleteProjectTask = (backlog_id, pt_id) => async (dispatch) => {
  if (
    window.confirm(
      `You are deleting Project Task:${pt_id} This action cant be undone`
    )
  ) {
    await axios.delete(
      `https://projectmanagement-amar.herokuapp.com/api/backlog/${backlog_id}/${pt_id}`
    );
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: pt_id,
    });
  }
};
