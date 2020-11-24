package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DTO.InventoryDTO;
import org.generictech.InventoryTracker.service.InventoryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet class containing all methods to handle HTTP requests dealing with 
 * inventory items. 
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/inventory", "/inventory/*"})
public class InventoryServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(InventoryServlet.class);
	private InventoryService inventoryService = new InventoryService();

	/**
	 * no args constructor
	 */
	public InventoryServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("GET request to /inventory endpoint");
		
		if (req.getPathInfo() != null && req.getPathInfo().split("/").length == 2) {
			try {
				res.getWriter().append(om.writeValueAsString(inventoryService.searchInventory(req.getPathInfo().split("/")[1])));
				res.setContentType("application/json");
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		} else {
			try {
				res.getWriter().append(om.writeValueAsString(inventoryService.getAllInventory()));
				res.setContentType("application/json");
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
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("POST request to /inventory endpoint");
		
		if (req.getPathInfo() != null) {
			res.setStatus(400);
		} else {
			try {
				InventoryDTO inventoryData = om.readValue(req.getReader(), InventoryDTO.class);
				res.getWriter().append(om.writeValueAsString(inventoryService.insertInventory(inventoryData)));
				res.setContentType("application/json");
			} catch (JsonProcessingException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IOException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
}
