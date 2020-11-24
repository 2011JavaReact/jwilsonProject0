package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	 * Method to handle communications with the database to insert new inventory records. 
	 * @param inventoryData
	 * @return Inventory object with data that was inserted into the database. 
	 * @throws SQLException
	 */
	public Inventory insertInventory(InventoryDTO inventoryData) throws SQLException {
		String query = "INSERT INTO inventory"
				+ " (quantity, product_id, last_update_date, last_updated_by)"
				+ " VALUES"
				+ "(?,?,?,?)";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		SystemUserService userService = new SystemUserService();
		User u = userService.searchUsersByUsername(inventoryData.getUsername());
		stmt.setInt(1, inventoryData.getQuantity());
		stmt.setInt(2, inventoryData.getProductId());
		stmt.setString(3, inventoryData.getLastUpdateDate());
		stmt.setInt(4, u.getSystemUserId());
		if (stmt.executeUpdate() != 1) {
			throw new SQLException("No Rows Affected");
		}
		int id = 0;
		ResultSet keys = stmt.getGeneratedKeys();
		stmt.close();
		if (keys.next()) {
			id = keys.getInt(1);
		} else {
			throw new SQLException("ID generation failed");
		}
		connection.close();
		return searchInventoryById(id).get(0);
	}
}
