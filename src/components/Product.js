import React from 'react';
import Axios from 'axios';

//Returns table retrieved from database
const Product = ({product, id}) => {

    return (
        <tr>
            <td>{product.productId}</td>
            <td>{product.productName}</td>
            <td>{product.description}</td>
            <td>{product.unitPrice}</td>
            <td><button type="button" className="btn btn-danger">Delete</button></td>
        </tr>
    )
}

export default Product;
