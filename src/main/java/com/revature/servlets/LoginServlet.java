package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.controllers.ErrorController;
import com.revature.controllers.UserServiceImplementation;
import com.revature.enums.UserRole;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.UserServiceInterface;
import com.revature.controllers.EmployeeController;

//import com.revature.controllers.AuthController;
//import com.revature.controllers.ErrorController;
//import com.revature.controllers.UserController;

/**
 * Servlet implementation class FrontController
 */
public class LoginServlet extends HttpServlet {
	
	
	private UserServiceInterface userService = new UserServiceImplementation();
	
	private ObjectMapper om = new ObjectMapper();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticateLogin(request, response);
	}
	
	protected void authenticateLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		//be our front controller
		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
													req.getRequestURI().length());
		System.out.println(URI);
		switch (URI) {
			case "/login":{
				switch (req.getMethod()) {
					case "POST":{
						Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
						System.out.println(cred);
						User u = null;
						try {
							u = userService.login(cred.getUsername(), cred.getPassword());
							System.out.println(u);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//use your session to keep track of your user permission level
						HttpSession sess = req.getSession();
						
						if(u.getRole() == UserRole.EMPLOYEE) {
							sess.setAttribute("User-Role", "EMPLOYEE");
						}else if(u.getRole() == UserRole.FINANCE_MANAGER) {
							sess.setAttribute("User-Role", "FINANCE_MANAGER");		
						}
						sess.setAttribute("user", u);
						System.out.println(sess.getId());
						
						
						res.setStatus(200);
						res.getWriter().write(om.writeValueAsString(u));
						break;
					}
					default:{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
				
				}
				break;
			}
			default:{
				res.setStatus(404);
				res.getWriter().write("No Such Resource");
				break;
			}
			
		}
		
		
		
	}
//	
//	
//	protected void directControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		//to handle all internal errors/exceptions
//		try {
//			authenticateLogin(request, response);
//		}catch (Throwable t) {
////			errorController.handle(request, response, t);//go to the error controller
////			res.setStatus(400);
////			res.getWriter().write("Method Not Supported");
//		}
//	}

	

	

}
