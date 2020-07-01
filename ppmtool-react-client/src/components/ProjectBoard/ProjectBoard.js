import React, { Component } from "react";
import { Link } from "react-router-dom";
import BackLog from "./BackLog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBackLog } from "../../Actions/backLogActions";

class ProjectBoard extends Component {
  //constructor to handle errors

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBackLog(id);
  }

  render() {
    //important code
    const { id } = this.props.match.params;
    const { project_tasks } = this.props.backlog;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />

        <BackLog project_tasks={project_tasks}></BackLog>
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBackLog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
});
export default connect(mapStateToProps, { getBackLog })(ProjectBoard);
