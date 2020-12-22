package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;


public class DAODebuggerP1 {
	
	static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserPostgresDAO();
		ReimbursementDAO reimDAO = new ReimbursementPostgresDAO();
		
		
		try {
			User u =userDAO.getUser("colin", "s");
			System.out.println(u);
			System.out.println(reimDAO.getAllReimbursements());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
