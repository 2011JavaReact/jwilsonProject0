import React from 'react';
import {useState} from 'react';
import Axios from 'axios';

const url = 'http://localhost:8080/inventorytracker/inventory';
//const url = 'http://inventory.generictech.org:8080/inventorytracker/login';

export default class AddInventoryPage extends React.Component {
    //State management for form data
    constructor(props) {
        super(props);
        this.state = {
            quantity: 0,
            productId: 0,
            lastUpdateDate: "",
            username: ""
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
                    <h2>Add Inventory Record</h2>
                    <form onSubmit={this.handleSubmit} action="/view-inventory">
                        <div className="form-group">
                            <label htmlFor="quantity">Quantity:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter quantity" name="quantity" id="quantity" value={this.state.quantity} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="productid">Product ID:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter product ID" name="productId" id="productId" value={this.state.productId} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastUpdateDate">Date of Last Update:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter date of last update" name="lastUpdateDate" id="lastUpdateDate" value={this.state.lastUpdateDate} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="username">Last Updated By:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter username" name="username" id="username" value={this.state.username} onChange={this.handleChange} required></input>
                        </div>
                        <button type="submit" className="btn btn-primary" id="record-submit">Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}