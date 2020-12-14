import React from 'react';

//Returns table retrieved from database
const Inventory = ({inventory}) => {

    return (
        <tr>
            <td>{inventory.inventoryID}</td>
            <td>{inventory.quantity}</td>
            <td>{inventory.product.productId}</td> 
            <td>{inventory.lastUpdateDate}</td>
            <td>{inventory.lastUpdatedBy.username}</td>
            <td><button type="button" className="btn btn-danger">Delete</button></td>
        </tr>
    )
}

export default Inventory;