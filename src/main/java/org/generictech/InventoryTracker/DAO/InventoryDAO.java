package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.model.User;
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
		connection.close();
		return inventory;
	}
	
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
		connection.close();
		return inventory;
	}
	
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
		connection.close();
		return inventory;
	}
}
