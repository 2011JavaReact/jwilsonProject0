import React from 'react';
import {useState} from 'react';
import Axios from 'axios';

const url = 'http://localhost:8080/inventorytracker/systemuser';
//const url = 'http://inventory.generictech.org:8080/inventorytracker/login';

export default class AddUserPage extends React.Component {
    //State management for form data
    constructor(props) {
        super(props);
        this.state = {
            fName: "",
            lName: "",
            username: "",
            password: "",
            isManager: String(false)
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    //Changes form data state based on form attributes entered
    handleChange(event)  {
        this.setState({[event.target.name]: event.target.value});
    }

    //Post form data to endpoint
    handleSubmit(event) {
        event.preventDefault();
        Axios.post(
            url,
            this.state,
            {withCredentials: true}
        ).then(resp => console.log(resp));
    }

    render() {
        return(
        //Add new data
            <div>
                <div className="form-container">
                    <h2>Add User</h2>
                    <form onSubmit={this.handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="fName">First Name:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter first name" name="fName" id="fName" value={this.state.fName} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="lName">Last Name:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter last name" name="lName" id="lName" value={this.state.lName} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="username">Username:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter username" name="username" id="username" value={this.state.username} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter password" name="password" id="password" value={this.state.password} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="isManager">Manager (true or false):</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter isManager" name="isManager" id="isManager" value={this.state.isManager} onChange={this.handleChange} required></input>
                        </div>
                        <button type="submit" className="btn btn-primary" id="record-submit">Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}