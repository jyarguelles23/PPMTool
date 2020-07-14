import React, { Component } from "react";
import { Link } from "react-router-dom";
import BackLog from "./BackLog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBackLog } from "../../Actions/backLogActions";

class ProjectBoard extends Component {
  //constructor to handle errors
  constructor() {
    super();
    this.state = {
      errors: {},
    };
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBackLog(id);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    //important code
    const { id } = this.props.match.params;
    const { project_tasks } = this.props.backlog;
    const { errors } = this.state;
    let boardContent;

    const boardAlgorithm = (errors, project_tasks) => {
      if (project_tasks.length < 1) {
        if (errors.projectNotFound) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.projectNotFound}
            </div>
          );
        } else if (errors.projectIdentifier) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.projectIdentifier}
            </div>
          );
        } else {
          return (
            <div className="alert alert-danger text-center" role="alert">
              No project Tasks on this board
            </div>
          );
        }
      } else {
        return <BackLog project_tasks={project_tasks}></BackLog>;
      }
    };

    boardContent = boardAlgorithm(errors, project_tasks);
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />

        {boardContent}
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBackLog: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
  errors: state.errors,
});
export default connect(mapStateToProps, { getBackLog })(ProjectBoard);
