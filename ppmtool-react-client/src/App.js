import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import Header from "./components/Layout/Header";
import { Provider } from "react-redux";
import store from "./Store";
import UpdateProject from "./components/Project/UpdateProject";
function App() {
  return (
    //provider es de redux y es el q permite conectar las stores con los appjs
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
