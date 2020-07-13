import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import Header from "./components/Layout/Header";
import { Provider } from "react-redux";
import store from "./Store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import Login from "./components/UserManagment/Login";
import Register from "./components/UserManagment/Register";
import Landing from "./components/Layout/Landing";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "../src/Actions/types";
import { logout } from "../src/Actions/securityActions";
import SecuredRoutes from "./securityUtils/secureRoute";

//Esto es para que en todas las pagians siempre tenga en el header el jwt Token generado y asi aunque refresque la pagina el service de springboot siempre me traera la data del user logeado.
const jwtToken = localStorage.jwtToken;
if (jwtToken) {
  setJWTToken(jwtToken);
  const decode_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decode_jwtToken,
  });
  //Aqui manejamos la logica si el token expiro para deslogear
  const currentTime = Date.now() / 1000;
  if (decode_jwtToken.exp < currentTime) {
    //handle logout
    // window.location.href = "/";
    store.dispatch(logout());
    window.location.href = "/";
  }
}
function App() {
  return (
    //provider es de redux y es el q permite conectar las stores con los appjs
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          {
            //Public Routes
          }
          <Route exact path="/" component={Landing} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          {
            //Private Routes
          }
          <Switch>
            <SecuredRoutes exact path="/dashboard" component={Dashboard} />
            <SecuredRoutes exact path="/addProject" component={AddProject} />
            <SecuredRoutes
              exact
              path="/updateProject/:id"
              component={UpdateProject}
            />
            <SecuredRoutes
              exact
              path="/projectBoard/:id"
              component={ProjectBoard}
            />
            <SecuredRoutes
              exact
              path="/addProjectTask/:id"
              component={AddProjectTask}
            />
            <SecuredRoutes
              exact
              path="/updateProjectTask/:backlog_id/:pt_id"
              component={UpdateProjectTask}
            />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
