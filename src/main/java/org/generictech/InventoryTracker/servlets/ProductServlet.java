package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DAO.ProductDAO;
import org.generictech.InventoryTracker.DTO.ProductDTO;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.service.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Servlet implementation for ProductServlet. Servlet handles incoming connections
 * specifically dealing with products. 
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns={"/product", "/product/*"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(ProductServlet.class);
	private ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * Method for handling GET requests to /product and /product/* endpoints
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("GET request to /product");
		if (req.getPathInfo() != null && req.getPathInfo().split("/").length == 2) {
			try {
				ArrayList<Product> p = productService.searchProducts(req.getPathInfo().split("/")[1]);
				res.getWriter().append(om.writeValueAsString(p));
			} catch (NumberFormatException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		} else if (req.getPathInfo().split("/").length > 2) {
			res.setStatus(400);
		}else {
			try {
				res.getWriter().append(om.writeValueAsString(productService.getAllProducts()));
			} catch (JsonProcessingException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IOException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
		res.setContentType("application/json");
	}

	/**
	 * Method for handling post requests to the /product and /product/* endpoints. 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("POST request to /product");
		if (req.getPathInfo() != null) {
			res.setStatus(400);
		} else {
			try {
				ProductDTO productData = om.readValue(req.getReader(), ProductDTO.class);
				Product p = productService.insertProduct(productData);
				res.getWriter().append(om.writeValueAsString(p));
			} catch (SQLException e) {
				e.printStackTrace();
				res.setStatus(400);
			} catch (UnrecognizedPropertyException e) {
				res.setStatus(400);
			}
			res.setContentType("application/json");
		}
	}

	/**
	 * Method to hand put requests to the /product and /product/* endpoints.
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("PUT request to /product");
		if (req.getPathInfo() == null || req.getPathInfo().split("/").length != 2) {
			res.setStatus(400);
		} else {
			String[] params = req.getPathInfo().split("/");
			try {
				ProductDTO productData = om.readValue(req.getReader(), ProductDTO.class);
				Product p = productService.updateProduct(productData, Integer.parseInt(params[1]));
				res.getWriter().append(om.writeValueAsString(p));
			} catch (SQLException e) {
				e.printStackTrace();
				res.setStatus(400);
			}
		}
		res.setContentType("application/json");
	}

	/**
	 * Method for handling DELETE requests at the /product endpoints. 
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("DELETE request to /product");
		if (req.getPathInfo() == null || req.getPathInfo().split("/").length != 2) {
			res.setStatus(400);
		} else {
			try {
				boolean success = productService.deleteProduct(Integer.parseInt(req.getPathInfo().split("/")[1]));
				if (success) {
					res.setStatus(204);
				} else {
					res.setStatus(400);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				res.setStatus(400);
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
	
	
	

}
