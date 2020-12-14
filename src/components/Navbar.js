import React from 'react';
import {NavLink} from 'react-router-dom';

const Navbar = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-sm fixed-top">
                <div className="my-container">
                    <h1 id="logo">Inventory Tracker</h1>
                    <ul className="navbar-nav">
                        <li className="nav-item">
                        <NavLink exact to='/'>Home</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/view-inventory'>View Inventory</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/view-products'>View Products</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/add-inventory'>Add Inventory</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/add-product'>Add Product</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/add-user'>Add User</NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}

export default Navbar;