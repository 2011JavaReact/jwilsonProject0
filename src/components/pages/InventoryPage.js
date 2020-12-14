import React, {useEffect, useState} from 'react';
import Axios from 'axios';
import Inventory from '../Inventory';

const InventoryPage = () => {

    const [inventory, setInventory] = useState([]);
    const url = 'http://localhost:8080/inventorytracker/inventory';
    //const url = 'http://inventory.generictech.org:8080/inventorytracker/inventory';

    const getInventory = async () => {
        const resp = await Axios.get(
            url, 
            {withCredentials:true});

        setInventory(resp.data);
        console.log(resp);
    }

    useEffect(() => {
        getInventory();
    }, []);

    //Table view from DB
    return (
        <div className="container">
            <h2><b>Inventory</b></h2>            
            <table className="table table-dark table-striped" id="inventory-table">
                <thead>
                    <tr>
                        <th>Inventory ID</th>
                        <th>Quantity</th>
                        <th>Product ID</th>
                        <th>Last Update Date</th>
                        <th>Last Updated By</th>
                    </tr>
                </thead>
                <tbody id="inventory-table-data">
                    {
                        inventory.map(inventory => (
                            <Inventory inventory={inventory} key={inventory.inventoryID}/>
                        ))
                    }
                </tbody>
            </table>
        </div>
    );
}

export default InventoryPage;