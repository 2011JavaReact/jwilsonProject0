import React from 'react';
import Axios from 'axios';
//import {useCookies} from 'react-cookie';

const HomePage = () => {

    /* const [cookies, setCookie] = useCookies(["user"]);

    const handleCookie = () => {
        setCookie("user", "frank", {
            path: '/'
        });
    } */

    const userLogin = (e) => {

        e.preventDefault();

       /*  fetch("http://inventory.generictech.org:8080/inventorytracker/login", {
            method: 'POST',
            mode: 'cors',
            credentials: 'include',
            body: JSON.stringify({userData})
        }).then(resp => resp.json()).then(data => console.log(data)); */

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const userData = {
            username:username, 
            password:password
        }

        const url = 'http://localhost:8080/inventorytracker/login';
        //const url = 'http://inventory.generictech.org:8080/inventorytracker/login';

        Axios.post(
            url, 
            userData, 
            {withCredentials:true}
        ).then(resp => console.log(resp));
    }

    return (
        <div id="showcase">
            <div className="my-container">
                <div id="showcase-content">
                    <h1>Inventory Tracker</h1>
                </div>
                <div className="form-container">
                    <h2>Login</h2>
                    <form onSubmit={(e) => this.userLogin(e)} action="/view-inventory" id="login-form">
                        <div className="form-group">
                            <label htmlFor="username">Username:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter username" id="username" required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="pwd">Password:</label>
                            <input type="password" className="form-control col-sm-4" placeholder="Enter password" id="password" required></input>
                        </div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    ); 
    
}

export default HomePage;