# Inventory Tracker
# Product Description
This application acts as the backend for a simple Inventory Tracking application. This will allow a team to keep track of products and the inventory numbers of those products. There is even a unitprice value fore each product to enable tracking of sales inventory. 

Users of this application can read, add, update, and remove products, as well as their related inventory records. Managers can even see more details such as which user updated the inventory last. 

# API Description
The URL for this API will contain your server IP address and port, followed by:
> /inventorytracker/{endpoint}/{options}

## /product Endpoint
This endpoint handles all operations dealing with products.
### GET /product
Get all products in the database as an array. Example output below:
```json
[
    {
        "productId": 1,
        "productName": "12in Front Bike Tire",
        "description": "Front tire for midsize mountain bike.",
        "unitPrice": 24.99
    },
    {
        "productId": 2,
        "productName": "UP Comfort Bike Seat",
        "description": "Ultra Plush line bike seat",
        "unitPrice": 19.99
    }
]
```
Searching can also be accomplished by adding the desired id or partial product name to the URI.
> /product/{id}

or
> /product/{partial_product_name_string}

### POST /product
This endpoint creates a new product. Send product data in a JSON string fomated as follows
```json
{
    "productName": "Test Product",
    "description": "A Product for testing",
    "unitPrice": 9.99
}
```
The response will also be in JSON showing the data of the new product. Including the product id for later use. 
```json
{
    "productId": 4,
    "productName": "Test Product",
    "description": "A Product for testing",
    "unitPrice": 9.99
}
```

### PUT /product
Use this HTTP request to update a product. 
The id value of the product you want to update should be included in the URI (example below):
> /product/{id}

At this point all data needs to be included with the update request. 
```json 
{
    "productName": "Test Product",
    "description": "A Product made for testing",
    "unitPrice": 9.99
}
```
The response will be the same as creating a new product, but with the updated information.
```json
{
    "productId": 4,
    "productName": "Test Product",
    "description": "A Product made for testing",
    "unitPrice": 9.99
}
```

### DELETE /product
DELETE requests will delete the specified product from the database. Requests should be sent with the product id in the URI:
> /product/{id}

If the DELETE is successful, the response will be a status code 204 response. 

## /inventory Endpoint
This endpoint deals with all requests pertaining to inventory records. 
### GET /inventory
A GET request to the /inventory endpoint will return the entire inventory. Example output below:
```json
[
    {
        "inventoryID": 1,
        "quantity": 26,
        "product": {
            "productId": 1,
            "productName": "12in Front Bike Tire",
            "description": "Front tire for midsize mountain bike.",
            "unitPrice": 24.99
        },
        "lastUpdateDate": "2020-11-24",
        "lastUpdatedBy": {
            "systemUserId": 1,
            "username": "imaster",
            "manager": true
        }
    },
    {
        "inventoryID": 2,
        "quantity": 14,
        "product": {
            "productId": 2,
            "productName": "UP Comfort Bike Seat",
            "description": "Ultra Plush line bike seat",
            "unitPrice": 19.99
        },
        "lastUpdateDate": "2020-11-24",
        "lastUpdatedBy": {
            "systemUserId": 1,
            "username": "imaster",
            "manager": true
        }
    },
  ...
]
```
Searching can also be accomplished by adding the product id or partial product name to the URI. (Searching is done based upon products because they are usually the important part of the inventory record.)
> /inventory/{productId}

or 
> /inventory/{partial_product_name_string}
### POST /inventory
A POST request to the /inventory enpoint will insert a new inventory record into the databse. 
Example input :
```json
{
    "quantity": 40,
    "productId": 4,
    "lastUpdateDate": "2020-11-23",
    "username": "imaster"
}
```
Example response:
```json
{
    "inventoryID": 3,
    "quantity": 40,
    "product": {
        "productId": 4,
        "productName": "Test Product",
        "description": "A Product made for testing",
        "unitPrice": 9.99
    },
    "lastUpdateDate": "2020-11-23",
    "lastUpdatedBy": {
        "systemUserId": 1,
        "username": "imaster",
        "manager": true
    }
}
```
### PUT /inventory
A PUT request to the /inventory enpoint will update inventory records on the database. The id value of the inventory record you want to update should be included in the URI
> /inventory/{id}

```json
{
    "quantity": 35,
    "productId": 4,
    "lastUpdateDate": "2020-11-23",
    "username": "imaster"
}
```
All fields are necessary, but each can be altered as desired.

The response should contain the data within the inventory record.
```json 
{
    "inventoryID": 5,
    "quantity": 35,
    "product": {
        "productId": 4,
        "productName": "Test Product",
        "description": "A Product made for testing",
        "unitPrice": 9.99
    },
    "lastUpdateDate": "2020-11-23",
    "lastUpdatedBy": {
        "systemUserId": 1,
        "username": "imaster",
        "manager": true
    }
}
```
### DELETE /inventory
A DELETE request to the /inventory endpoint will delete inventory records from the database. Requests to delete should detail the id of the item to be deleted in the URI.
>/inventory/{id}

## /login Endpoint
This endpoint deals with login operations. 
### POST /login
Credentials should be sent in JSON format with a POST request in the following format:
```json
{
    "username": "notMaster2",
    "password": "another$ecretPa$$word"
}
```
If authentication is successful a response status 200 will be returned, along with non confidential user detials for front end operations if desired. (Format below).
```json
{
    "systemUserId": 2,
    "username": "notMaster2",
    "manager": false
}
```

## /systemuser Endpoint
This endpoint deals with users.
### POST /systemuser
A POST request to this endpoint will create a new user. 
Include necessary data as a JSON string. 
```json
{
    "fName": "Test",
    "lName": "Tester",
    "username": "Ttester",
    "password": "anOhSo$ecretPassword",
    "isManager": true
}
```
A 200 status response will be returned with basic user data. 
```json
{
    "systemUserId": 3,
    "username": "Ttester",
    "manager": true
}
```
An unsuccessful login will return a response status 401.
### DELETE /systemuser
This request will delete a system user. Requests should be sent with the id of the desired user in the URI.
> /systemuser/{id}

# Deployment
To deploy this application clone the repository, and install/configure the following technologies on your server. 
   - Tomcat
   - JAVA 8
   - maven
   - postgresql

also be sure to set the following environment variables
   - CATALINA_HOME
   - JAVA_HOME
   - DB_URL
   - DB_USERNAME
   - DB_PASSWORD

Once the configuration of these tools has been completed:

1. run the InventoryCreate.sql on your postgresql instance to create the database. 
    - be sure to comment out insert statements if you do not want test data in your database.
2. run the following command to package the project into a war file.
> mvn clean package 

3. copy the war file from the target directory into the webapps directory of your tomcat installation. 
4. start your tomcat server and begin testing.

