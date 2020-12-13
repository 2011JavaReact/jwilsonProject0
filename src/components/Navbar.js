import React from 'react';
import {NavLink} from 'react-router-dom';

const Navbar = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
                <div className="my-container">
                    <h1 id="logo">
                        <NavLink to='/'>Inventory Tracker</NavLink>
                    </h1>
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
                        <NavLink exact to='/add-products'>Add Products</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/add-user'>Add User</NavLink>
                        </li>
                        <li className="nav-item">
                        <NavLink exact to='/logout'>Logout</NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}

export default Navbar;