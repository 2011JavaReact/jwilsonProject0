import React from 'react';
import {useState} from 'react';
import Axios from 'axios';

const AddPage = () => {
    //State management for form data
    const [formData, setFormData] = useState({});
    //Post form data to endpoint
    const addRecord = async (e) => {
        e.preventDefault();
        const resp = await Axios.post('http://inventory.generictech.org:8080/inventory', formData);
        console.log(resp);
    }
    //Changes form data state based on form attributes entered
    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    }

    return(
        //Add new data
        <div>
            <div className="form-container">
                <h2>Add Inventory Record</h2>
                <form onSubmit={addRecord}>
                    <div className="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="text" className="form-control col-sm-4" placeholder="Enter quantity" name="quantity" id="quantity" value={formData["quantity"]} onChange={handleChange} required></input>
                    </div>
                    <div className="form-group">
                        <label for="productid">Product ID:</label>
                        <input type="text" className="form-control col-sm-4" placeholder="Enter productID" name="productid" id="productid" value={formData["productid"]} onChange={handleChange} required></input>
                    </div>
                    <div className="form-group">
                        <label for="lastUpdateDate">Date of Last Update:</label>
                        <input type="text" className="form-control col-sm-4" placeholder="Enter date of last update" name="lastUpdateDate" id="lastUpdateDate" value={formData["lastUpdateDate"]} onChange={handleChange} required></input>
                    </div>
                    <div className="form-group">
                        <label for="username">Username:</label>
                        <input type="text" className="form-control col-sm-4" placeholder="Enter username" name="username" id="username" value={formData["username"]} onChange={handleChange} required></input>
                    </div>
                    <button type="submit" className="btn btn-primary" id="record-submit">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default AddPage;