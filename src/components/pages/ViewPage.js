import React, {useEffect, useState} from 'react';
import Axios from 'axios';
import Product from '../Product';

//Functional component
const ViewPage  = () => {
    const [products, setProducts] = useState([]);

    const getProduct = async () => {
        const resp = await Axios.get(
            'http://inventory.generictech.org:8080/inventorytracker/product', 
            {withCredentials:true});

        setProducts(resp.data);
        console.log(resp);
    }

    useEffect(() => {
        getProduct();
    }, []);

    //Table view from DB
    return (
        <div className="container">
            <h2>Product Table</h2>            
            <table className="table table-dark table-striped" id="product-table">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody id="product-table-data">
                    {
                        products.map(product => (
                            <Product product={product} key={product.productId}/>
                        ))
                    }
                </tbody>
            </table>
        </div>
    );
}

export default ViewPage;