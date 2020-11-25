package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DTO.CredentialsDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.service.LoginService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to handle HTTP requests to the /login endpoint
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(LoginServlet.class);
	private LoginService loginService = new LoginService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("POST request to /login");
		
		try {
			CredentialsDTO credentials = om.readValue(req.getReader(), CredentialsDTO.class);
			User u = loginService.login(credentials);
			if (u != null) {
				HttpSession session =  req.getSession();
				session.setAttribute("systemUserId", u.getSystemUserId());
				session.setAttribute("username", u.getUsername());
				session.setAttribute("isManager", u.isManager());
				res.getWriter().append(om.writeValueAsString(u));
				res.setContentType("application/json");				
			} else {
				res.setStatus(401);
			}
		} catch (JsonProcessingException e) {
			res.setStatus(400);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			res.setStatus(500);
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
