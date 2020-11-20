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
		
		return products;
	}
}
