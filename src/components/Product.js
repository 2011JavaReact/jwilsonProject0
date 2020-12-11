import React from 'react';

const Product = ({product}) => {

    return (
        <tr>
            <td>{product.productId}</td>
            <td>{product.productName}</td>
            <td>{product.description}</td>
            <td>{product.unitPrice}</td>
        </tr>
    )
}

export default Product;