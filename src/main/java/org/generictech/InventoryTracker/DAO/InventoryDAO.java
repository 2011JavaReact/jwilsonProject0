package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DTO.InventoryBasicDTO;
import org.generictech.InventoryTracker.DTO.InventoryDTO;
import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.service.ProductService;
import org.generictech.InventoryTracker.service.SystemUserService;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database access for inventory records. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class InventoryDAO {
	
	/**
	 * Method to interact with the database to get all inventory records and 
	 * return them as an ArrayList.
	 * @return ArrayList<Inventory> of all inventory records. 
	 * @throws SQLException
	 */
	public ArrayList<Inventory> getAllInventory() throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id";
		ArrayList<Inventory> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new Inventory(result.getInt(1), result.getInt(2), p, result.getString(4), u));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method that interacts with the database to all inventory records from the database.
	 * This method is identical in implementation to the above getAllInventory() method, except this 
	 * one returns an ArrayList<InventoryBasicDTO> in order to accommodate basic users view.  
	 * @return
	 * @throws SQLException
	 * @see {@link InventoryDAO#getAllInventory()}
	 */
	public ArrayList<InventoryBasicDTO> basicGetAllInventory() throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id";
		ArrayList<InventoryBasicDTO> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new InventoryBasicDTO(result.getInt(1), result.getInt(2), p, result.getString(4)));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method to interact with the database and search by the product ID value
	 * of the associated product. As the main reason for an inventory list is to see
	 * how many of a particular product are available it is more logical to search by product 
	 * ID than inventory id. 
	 * @param id value for the desired product inventory.
	 * @return  ArrayList<Inventory> of the results that match the search condition (should only be a single result)
	 * @throws SQLException
	 */
	public ArrayList<Inventory> searchInventoryById(int id) throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id"
				+ " WHERE i.product_id = ?";
		ArrayList<Inventory> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new Inventory(result.getInt(1), result.getInt(2), p, result.getString(4), u));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method to handle database interactions for searching inventory records by product_id. 
	 * This method is similar to the above searchInventoryById method, and differs in its return
	 * type of ArrayList<InventoryBasicDTO> to accommodate basic users. 
	 * @param id
	 * @return ArrayList<InventoryBasicDTO>
	 * @throws SQLException
	 * @see {@link InventoryDAO#searchInventoryById(int)}
	 */
	public ArrayList<InventoryBasicDTO> basicSearchInventoryById(int id) throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id"
				+ " WHERE i.product_id = ?";
		ArrayList<InventoryBasicDTO> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new InventoryBasicDTO(result.getInt(1), result.getInt(2), p, result.getString(4)));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method to interact with the database and provide functionality to search inventory
	 * records by product name. 
	 * @param name of the desired product
	 * @return ArrayList<Inventory> of all the inventory records that match the search condition. 
	 * @throws SQLException
	 */
	public ArrayList<Inventory> searchInventoryByProductName(String name) throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id"
				+ " WHERE LOWER(product_name) LIKE LOWER(?)";
		ArrayList<Inventory> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new Inventory(result.getInt(1), result.getInt(2), p, result.getString(4), u));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method to handle database interactions for searching inventory records by product name. This
	 * method is similar to the above searchInventoryByName method, and only differs in its return type, allowing for different
	 * views between managers and basic users.
	 * @param name String to search for
	 * @return ArrayList<InventoryBasicDTO> 
	 * @throws SQLException
	 */
	public ArrayList<InventoryBasicDTO> basicSearchInventoryByProductName(String name) throws SQLException {
		String query = "SELECT inventory_id, quantity, i.product_id,"
				+ " last_update_date, last_updated_by, p.product_name, p.description, p.unitprice"
				+ ", username, ismanager"
				+ " FROM Inventory i INNER JOIN product p"
				+ " ON i.product_id = p.product_id INNER JOIN System_user su"
				+ " ON i.last_updated_by = su.system_user_id"
				+ " WHERE LOWER(product_name) LIKE LOWER(?)";
		ArrayList<InventoryBasicDTO> inventory = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			Product p = new Product(result.getInt("product_id"), result.getString("product_name")
					, result.getString("description"), result.getDouble("unitprice"));
			User u = new User(result.getInt("last_updated_by"), result.getString("username"), result.getBoolean("ismanager"));
			inventory.add(new InventoryBasicDTO(result.getInt(1), result.getInt(2), p, result.getString(4)));
		}
		stmt.close();
		connection.close();
		return inventory;
	}
	
	/**
	 * Method to handle communications with the database to insert new inventory records. 
	 * @param inventoryData
	 * @return Inventory object with data that was inserted into the database. 
	 * @throws SQLException
	 */
	public Inventory insertInventory(InventoryDTO inventoryData) throws SQLException, IndexOutOfBoundsException {
		String query = "INSERT INTO inventory"
				+ " (quantity, product_id, last_update_date, last_updated_by)"
				+ " VALUES"
				+ "(?,?,TO_DATE(?, 'YYYY-MM-DD'),?)";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		SystemUserService userService = new SystemUserService();
		ProductService productService = new ProductService();
		User u = userService.searchUsersByUsername(inventoryData.getUsername());
		Product p = productService.searchProducts(inventoryData.getProductId() +  "").get(0);
		stmt.setInt(1, inventoryData.getQuantity());
		stmt.setInt(2, inventoryData.getProductId());
		stmt.setString(3, inventoryData.getLastUpdateDate());
		stmt.setInt(4, u.getSystemUserId());
		if (stmt.executeUpdate() != 1) {
			throw new SQLException("No Rows Affected");
		}
		int id = 0;
		ResultSet keys = stmt.getGeneratedKeys();
		if (keys.next()) {
			id = keys.getInt(1);
		} else {
			throw new SQLException("ID generation failed");
		}
		stmt.close();
		connection.close();
		return new Inventory(id, inventoryData.getQuantity(), p, inventoryData.getLastUpdateDate(), u);
	}
	
	/**
	 * Method to interact with the database to insert and inventory record. This method is
	 * identical to insertInventory() except it returns an InventoryBasicDTO object instead of an inventory object.
	 * This is to facilitate different return views for managers and basic users.
	 * @param inventoryData
	 * @return InventoryBasicDTO object with basic inventory data.
	 * @throws SQLException
	 * @throws IndexOutOfBoundsException
	 * @see InventoryDAO#insertInventory(InventoryDTO)
	 */
	public InventoryBasicDTO basicInsertInventory(InventoryDTO inventoryData) throws SQLException, IndexOutOfBoundsException {
		String query = "INSERT INTO inventory"
				+ " (quantity, product_id, last_update_date, last_updated_by)"
				+ " VALUES"
				+ "(?,?,TO_DATE(?, 'YYYY-MM-DD'),?)";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		SystemUserService userService = new SystemUserService();
		ProductService productService = new ProductService();
		User u = userService.searchUsersByUsername(inventoryData.getUsername());
		Product p = productService.searchProducts(inventoryData.getProductId() +  "").get(0);
		stmt.setInt(1, inventoryData.getQuantity());
		stmt.setInt(2, inventoryData.getProductId());
		stmt.setString(3, inventoryData.getLastUpdateDate());
		stmt.setInt(4, u.getSystemUserId());
		if (stmt.executeUpdate() != 1) {
			throw new SQLException("No Rows Affected");
		}
		int id = 0;
		ResultSet keys = stmt.getGeneratedKeys();
		if (keys.next()) {
			id = keys.getInt(1);
		} else {
			throw new SQLException("ID generation failed");
		}
		stmt.close();
		connection.close();
		return new InventoryBasicDTO(id, inventoryData.getQuantity(), p, inventoryData.getLastUpdateDate());
	}
	
	/**
	 * Method to handle database interactions dealing with updating an entire inventory record. 
	 * @param inventoryData 
	 * @param id value of inventory record to be updated. 
	 * @return Inventory object with updated inventory data. 
	 * @throws SQLException
	 */
	public Inventory updateInventory(InventoryDTO inventoryData, int id) throws SQLException {
		String query = "UPDATE inventory"
				+ " SET quantity = ?"
				+ " , product_id = ?"
				+ " , last_update_date = TO_DATE(?, 'YYY-MM-DD')"
				+ " , last_updated_by = ?"
				+ " WHERE inventory_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		SystemUserService userService = new SystemUserService();
		User u = userService.searchUsersByUsername(inventoryData.getUsername());
		ProductService productService = new ProductService();
		Product p = productService.searchProducts(inventoryData.getProductId() +  "").get(0);
		stmt.setDouble(1, inventoryData.getQuantity());
		stmt.setInt(2, inventoryData.getProductId());
		stmt.setString(3, inventoryData.getLastUpdateDate());
		stmt.setInt(4, u.getSystemUserId());
		stmt.setInt(5, id);
		if (stmt.executeUpdate() != 1) {
			throw new SQLException("No Rows Affected");
		}
		stmt.close();
		connection.close();
		return new Inventory(id, inventoryData.getQuantity(), p, inventoryData.getLastUpdateDate(), u);
	}
	
	/**
	 * Method to handle database interactions to update inventory record values. 
	 * This method is similar to the above updateInventory method, but differs int he return type
	 * to accommodate basic users. 
	 * @param inventoryData
	 * @param id
	 * @return InventoryBasicDTO
	 * @throws SQLException
	 * @see {@link InventoryDAO#updateInventory(InventoryDTO, int)}
	 */
	public InventoryBasicDTO basicUpdateInventory(InventoryDTO inventoryData, int id) throws SQLException {
		String query = "UPDATE inventory"
				+ " SET quantity = ?"
				+ " , product_id = ?"
				+ " , last_update_date = TO_DATE(?, 'YYY-MM-DD')"
				+ " , last_updated_by = ?"
				+ " WHERE inventory_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		SystemUserService userService = new SystemUserService();
		User u = userService.searchUsersByUsername(inventoryData.getUsername());
		ProductService productService = new ProductService();
		Product p = productService.searchProducts(inventoryData.getProductId() +  "").get(0);
		stmt.setDouble(1, inventoryData.getQuantity());
		stmt.setInt(2, inventoryData.getProductId());
		stmt.setString(3, inventoryData.getLastUpdateDate());
		stmt.setInt(4, u.getSystemUserId());
		stmt.setInt(5, id);
		if (stmt.executeUpdate() != 1) {
			throw new SQLException("No Rows Affected");
		}
		stmt.close();
		connection.close();
		return new InventoryBasicDTO(id, inventoryData.getQuantity(), p, inventoryData.getLastUpdateDate());
	}
	
	/**
	 * Method to handle database interactions to delete inventory records.
	 * @param id
	 * @return boolean value to express success of delete operation.
	 * @throws SQLException
	 */
	public boolean deleteInventory(int id) throws SQLException {
		String query = "DELETE FROM inventory"
				+ " WHERE inventory_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		if (stmt.executeUpdate() == 1 ) {
			stmt.close();
			connection.close();
			return true;
		} else {
			throw new SQLException("No Rows Affected");
		}
	}
}
