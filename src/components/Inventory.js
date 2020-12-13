import React from 'react';

const Inventory = ({inventory}) => {

    return (
        <tr>
            <td>{inventory.inventoryID}</td>
            <td>{inventory.quantity}</td>
            <td>{inventory.product.productId}</td> 
            <td>{inventory.lastUpdateDate}</td>
            <td>{inventory.lastUpdatedBy.username}</td>
        </tr>
    )
}

export default Inventory;