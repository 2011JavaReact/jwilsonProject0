import React from 'react';
import {useState} from 'react';
import Axios from 'axios';

const url = 'http://localhost:8080/inventorytracker/product';
//const url = 'http://inventory.generictech.org:8080/inventorytracker/login';

export default class AddProductPage extends React.Component {
    //State management for form data
    constructor(props) {
        super(props);
        this.state = {
            productName: "",
            description: "",
            unitPrice: 0
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
                    <h2>Add Product</h2>
                    <form onSubmit={this.handleSubmit} action="/view-products">
                        <div className="form-group">
                            <label htmlFor="productName">Product Name:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter product name" name="productName" id="productName" value={this.state.productName} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="description">Description:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter description" name="description" id="description" value={this.state.description} onChange={this.handleChange} required></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="unitPrice">Price:</label>
                            <input type="text" className="form-control col-sm-4" placeholder="Enter price" name="unitPrice" id="unitPrice" value={this.state.unitPrice} onChange={this.handleChange} required></input>
                        </div>
                        <button type="submit" className="btn btn-primary" id="record-submit">Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}