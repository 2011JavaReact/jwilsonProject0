package org.generictech.InventoryTracker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.ProductDAO;
import org.generictech.InventoryTracker.DTO.ProductDTO;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.utils.NumericUtility;

/**
 * Class to handle business logic dealing with products.
 * @author Jaden Wilson
 * @since 1.0
 */
public class ProductService {
	
	/**
	 * Field for productDAO instance for utilizing DAO methods
	 */
	private ProductDAO productDAO = new ProductDAO();

	/**
	 * Method to get all products from the database. 
	 * @see ProductDAO#getAllProducts()
	 * @return ArrayList<Product> Array list of products. 
	 * @throws SQLException
	 */
	public ArrayList<Product> getAllProducts() throws SQLException {
		return productDAO.getAllProducts();
	}
	
	/**
	 * Method to allow searching of products by ID or by name. This method checks to see if the string
	 * parameter can be converted to an integer, and if so searches by ID. Otherwise it searches by product name. 
	 * @param param retreived from the search request. 
	 * @return ArrayList<Product> representing the search results from the database. 
	 * @throws SQLException
	 */
	public ArrayList<Product> searchProducts(String param) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();
		if (NumericUtility.isInt(param))  {
			products = searchProductById(Integer.parseInt(param));
		} else {
			products = searchProductByName(param);
		}
		return products;
	}
	
	/**
	 * Method to handle new product inserts. 
	 * @param productData
	 * @return Product object with new product details. 
	 */
	public Product insertProduct(ProductDTO productData) throws SQLException {
		return productDAO.insertProduct(productData);
	}
	
	/**
	 * Method to hand updating an entire product. This method will update each field with data 
	 * specified in the productData object.
	 * @param productData with updated information. 
	 * @param id int value for the ID of the desired product to update
	 * @return Product with updated data. 
	 * @throws SQLException
	 */
	public Product updateProduct(ProductDTO productData, int id) throws SQLException {
		return productDAO.updateProduct(productData, id);
	}
	
	/**
	 * Method to handle delete operations of products
	 * @param id of product to be deleted
	 * @return boolean stating if the delete was successful. 
	 * @throws SQLException
	 */
	public boolean deleteProduct(int id) throws SQLException {
		return productDAO.deleteProduct(id);
	}
	
	/**
	 * Helper method to get a single product from the database, searching by specified id value.
	 * @see ProductService#searchProducts(String) 
	 * @param id ID value of desired product
	 * @return product object with product details from the database
	 * @throws SQLException
	 */
	private ArrayList<Product> searchProductById(int id) throws SQLException {
		return productDAO.searchProductById(id);
	}
	
	/**
	 * Helper method to implement searching by product name.
	 * @see ProductService#searchProducts(String) 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Product> searchProductByName(String name) throws SQLException {
		return productDAO.searchProductByName(name);
	}
	
}
