import logo from './logo.svg';
import './App.css';
import HomePage from './components/pages/HomePage';
import ViewPage from './components/pages/ViewPage';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import AddPage from './components/pages/AddPage';
import Navbar from './components/Navbar';


function App() {
  return (
    //Routing for HTML pages
    <Router>
      <Navbar/>
      <Switch>
        <Route exact path='/' component={HomePage}/>
        <Route exact path='/view-inventory' component={ViewPage}/>
        <Route exact path='/add' component={AddPage}/>
        <Route path='/' render={()=> <div>404 Not Found</div>}/>
      </Switch>
    </Router>
  );
}

export default App;
