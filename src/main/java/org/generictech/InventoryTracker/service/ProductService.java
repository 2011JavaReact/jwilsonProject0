package org.generictech.InventoryTracker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.ProductDAO;
import org.generictech.InventoryTracker.model.Product;

/**
 * Class to handle business logic dealing with products.
 * @author Jaden Wilson
 * @since 1.0
 */
public class ProductService {
	
	private ProductDAO productDAO = new ProductDAO();

	public ArrayList<Product> getAllProducts() throws SQLException {
		return productDAO.getAllProducts();
	}
}
