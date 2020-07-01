import React, { Component } from "react";
import { Link } from "react-router-dom";
import BackLog from "./BackLog";

class ProjectBoard extends Component {
  render() {
    //important code
    const { id } = this.props.match.params;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />

        <BackLog></BackLog>
      </div>
    );
  }
}

export default ProjectBoard;
