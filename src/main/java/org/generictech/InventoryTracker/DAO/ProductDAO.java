package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database interactions for product records
 * @author Jaden Wilson
 * @since 1.0
 */
public class ProductDAO {
	/**
	 * Method that queries the database for all product records and coverts those records 
	 * into product objects, and returns an array list of those product objects. 
	 * @return ArrayList of products as returned from the database.
	 */
	public ArrayList<Product> getAllProducts() throws SQLException{
		String query = "SELECT * FROM product";
		ArrayList<Product> products = new ArrayList<>();
		
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			products.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
		}
		connection.close();
		return products;
	}
	
	/**
	 * Method that queries the database for a single product with a specified ID value.
	 * Because each ID value is unique, this query should only return a single product. 
	 * @param id ID value of the desired product.
	 * @return Product
	 * @throws SQLException
	 */
	public ArrayList<Product> searchProductById(int id) throws SQLException {
		String query = "SELECT * FROM product WHERE product_id = ?";
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		result.next();
		products.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
		connection.close();
		return products;
	}
	
	/**
	 * Method that searches for products based upon the product name. 
	 * @param name Name of the desired product. 
	 * @return ArrayList of the products that meet the search criteria. 
	 * @throws SQLException
	 */
	public ArrayList<Product> searchProductByName(String name) throws SQLException {
		String query = "SELECT * FROM product WHERE LOWER(product_name) LIKE LOWER(?)";
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			products.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
		}
		connection.close();
		return products;
	}
}
