-- use code below to create inventory database if desired. 
-- DROP DATABASE IF EXISTS inventory;
-- CREATE DATABASE inventory;

DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS SYSTEM_USER;
DROP TABLE IF EXISTS product;
CREATE TABLE system_user 
(	system_user_id SERIAL PRIMARY KEY
,	fname VARCHAR(45) NOT NULL
,	lname VARCHAR(45) NOT NULL
, 	username VARCHAR(45) UNIQUE NOT NULL
,	PASSWORD VARCHAR(75) NOT NULL
,	isManager BOOLEAN NOT NULL
,	salt	VARCHAR(60) NOT NULL
);

CREATE TABLE product 
(	product_id SERIAL PRIMARY KEY
, 	product_name VARCHAR(255) NOT NULL
, 	description VARCHAR(255)
, 	unitPrice DECIMAL
);

CREATE TABLE inventory
(	inventory_id SERIAL PRIMARY KEY
,	quantity INT NOT NULL
,	product_id INT UNIQUE NOT NULL
, 	last_update_date DATE NOT NULL DEFAULT CURRENT_DATE
, 	last_updated_by INT NOT NULL
, 	CONSTRAINT fk_inventory_1 FOREIGN KEY (product_id) REFERENCES product(product_id)
,	CONSTRAINT fk_inventory_2 FOREIGN KEY (last_updated_by) REFERENCES system_user(system_user_id)
);


INSERT INTO product 
(	product_name 
,	description 
, 	unitprice 
)
VALUES 
(	'12in Front Bike Tire'
,	'Front tire for midsize mountain bike.'
, 	24.99
);

INSERT INTO product 
(	product_name
,	description 
, 	unitprice 
)
VALUES 
(	'UP Comfort Bike Seat'
,	'Ultra Plush line bike seat'
, 	19.99
);

INSERT INTO "system_user" 
(	fname 
, 	lname 
,	username 
, 	"password" 
,	isManager
, 	salt 
)
VALUES 
(	'Test'	
, 	'Tester'
,	'imaster'
,	'8be8c70f753773ad23fe03b26f51be4331a7fa50fd382bbf111193d93a2fea08'
,	TRUE 
,	'ea9fcbe3213fed02f71d51e6a16db272'
);


INSERT INTO inventory 
(	quantity 
, 	product_id  
, 	last_updated_by 
)
VALUES 
(	26
,	(SELECT product_id FROM product WHERE product_name = '12in Front Bike Tire')
, 	(SELECT system_user_id FROM "system_user" WHERE username = 'imaster')
);

INSERT INTO inventory 
(	quantity 
, 	product_id  
, 	last_updated_by 
)
VALUES 
(	14
,	(SELECT product_id FROM product WHERE product_name = 'UP Comfort Bike Seat')
, 	(SELECT system_user_id FROM "system_user" WHERE username = 'imaster')
);

SELECT * FROM inventory;