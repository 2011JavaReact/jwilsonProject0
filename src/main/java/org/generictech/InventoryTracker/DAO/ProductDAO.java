package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DTO.ProductDTO;
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
		stmt.close();
		if (result.next()) {
			products.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
		}
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
		stmt.close();
		while (result.next()) {
			products.add(new Product(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
		}
		connection.close();
		return products;
	}
	
	/**
	 * Method to insert a new product into the database
	 * @param productData data to be inserted
	 * @return Product with all the product details, including the ID from the database/ 
	 * @throws SQLException
	 */
	public Product insertProduct(ProductDTO productData) throws SQLException {
		String query = "INSERT INTO product"
				+ "(product_name, description, unitprice)"
				+ "VALUES"
				+ "(?,?,?)";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, productData.getProductName());
		stmt.setString(2, productData.getDescription());
		stmt.setDouble(3, productData.getUnitPrice());
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
		return new Product(id, productData.getProductName(), productData.getDescription(), productData.getUnitPrice());
	}
	
	/**
	 * Method to interact with the database to update all product fields. 
	 * @param productData object containing the data
	 * @param id int value for the ID of the desired product
	 * @return product object with updated details 
	 * @throws SQLException
	 */
	public Product updateProduct(ProductDTO productData, int id) throws SQLException {
		String query = "UPDATE product"
				+ " SET product_name = ?"
				+ " , description = ?"
				+ " , unitprice = ?"
				+ " WHERE product_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, productData.getProductName());
		stmt.setString(2, productData.getDescription());
		stmt.setDouble(3, productData.getUnitPrice());
		stmt.setInt(4, id);
		if (stmt.executeUpdate() != 1) {
			connection.close();
			return null;
		}
		stmt.close();
		connection.close();
		return new Product(id, productData.getProductName(), productData.getDescription(), productData.getUnitPrice());
	}
	
	/**
	 * Method to interact with the database to delete products.
	 * @param id of the product to be deleted
	 * @return boolean to state whether the delete was successful or not. 
	 * @throws SQLException
	 */
	public boolean deleteProduct(int id) throws SQLException {
		String query = "DELETE FROM product"
				+ " WHERE product_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		if (stmt.executeUpdate() == 1) {
			stmt.close();
			connection.close();
			return true;
		} else {
			stmt.close();
			connection.close();
			return false;
		}
	}
	
}
