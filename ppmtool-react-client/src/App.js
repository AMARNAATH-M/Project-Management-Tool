// import React,{Component} from "react";
import './App.css';
import Dashboard from './components/Dashboard';
import "bootstrap/dist/css/bootstrap.min.css";
import Header from './components/Layout/Header';

function App() {
  return (
    <div className="App">
      <Header></Header>
     <Dashboard />
   


    </div>
  );
}

export default App;