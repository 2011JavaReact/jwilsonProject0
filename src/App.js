import logo from './logo.svg';
import './App.css';
import HomePage from './components/pages/HomePage';
import ProductPage from './components/pages/ProductPage';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import AddInventoryPage from './components/pages/AddInventoryPage';
import Navbar from './components/Navbar';
import InventoryPage from './components/pages/InventoryPage';
import AddProductPage from './components/pages/AddProductPage';
import AddUserPage from './components/pages/AddUserPage';


function App() {
  return (
    //Routing for HTML pages
    <Router>
      <Navbar/>
      <Switch>
        <Route exact path='/' component={HomePage}/>
        <Route exact path='/view-products' component={ProductPage}/>
        <Route exact path='/view-inventory' component={InventoryPage}/>
        <Route exact path='/add-inventory' component={AddInventoryPage}/>
        <Route exact path='/add-product' component={AddProductPage}/>
        <Route exact path='/add-user' component={AddUserPage}/>
        <Route path='/' render={() => <div>404 Not Found</div>}/>
      </Switch>
    </Router>
  );
}

export default App;
