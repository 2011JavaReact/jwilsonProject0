package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DTO.SystemUserDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.service.SystemUserService;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Class to handle HTTP requests dealing with system users
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/systemuser", "/systemuser/*"})
public class SystemUserServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(InventoryServlet.class);
	private SystemUserService systemUserService = new SystemUserService();

	/**
	 * Method to handle POST requests to the /systemuser endpoints
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getPathInfo() != null) {
			res.setStatus(400);
		} else {
			try {
				SystemUserDTO systemUserData = om.readValue(req.getReader(), SystemUserDTO.class);
				res.getWriter().append(om.writeValueAsString(systemUserService.insertSystemUser(systemUserData)));
				res.setContentType("application/json");
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				res.setStatus(500);
				e.printStackTrace();
			}
		}
	}
}