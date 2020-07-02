import React, { Component } from "react";
import ProjectTask from "./ProjectTasks/ProjectTask";

class BackLog extends Component {
  render() {
    const { project_tasks } = this.props;

    const tasks = project_tasks.map((project_task) => (
      <ProjectTask key={project_task.id} project_task={project_task} />
    ));

    let todoItems = [];
    let inProgressItems = [];
    let doneItems = [];

    console.log(tasks);
    tasks.map((task) => {
      if (task.props.project_task.status === "TO_DO") {
        todoItems.push(task);
      } else if (task.props.project_task.status === "IN_PROGRESS") {
        inProgressItems.push(task);
      } else {
        doneItems.push(task);
      }
    });

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgressItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItems}
          </div>
        </div>
      </div>
    );
  }
}

export default BackLog;
