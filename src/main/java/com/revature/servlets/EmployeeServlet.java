package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Credentials;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.EmployeeServiceImplementation;
import com.revature.services.EmployeeServiceInterface;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmployeeServiceInterface empService = new EmployeeServiceImplementation();
	
	private ObjectMapper om = new ObjectMapper();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("getting session");
		HttpSession sess = req.getSession(false);		
		System.out.println(sess);
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals("Admin")) {
//			throw new UnauthorizedException();
//		}
		List<Reimbursement> allReimbursement = null;
		System.out.println("service controller");
		try {
			allReimbursement = empService.viewPastTickets((User)sess.getAttribute("user"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStatus(200);
//		res.addHeader("Content-Type", "application/json");
		if( allReimbursement == null) {
			allReimbursement = new ArrayList<>();
		}
		res.getWriter().write(om.writeValueAsString(allReimbursement));
	}

protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("getting session");
		HttpSession sess = req.getSession(false);		
		System.out.println(sess);
		User u = (User)sess.getAttribute("user");
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals("Admin")) {
//			throw new UnauthorizedException();
//		}
		Reimbursement r = null;
		System.out.println("service controller");
		try {
			r = om.readValue(req.getInputStream(), Reimbursement.class);
			r.setAuthorID(u.getUserId());
			System.out.println(r);
			r = empService.addReimbursementRequest(u, r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStatus(200);
//		res.addHeader("Content-Type", "application/json");
//		if( reimbursement == null) {
//			reimbursement = new ArrayList<>();
//		}
		res.getWriter().write(om.writeValueAsString(r));
	}


}
