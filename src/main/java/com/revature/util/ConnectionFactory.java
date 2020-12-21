package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory cf = new ConnectionFactory(1);
	
	public static ConnectionFactory getConnectionFactory() {
		return cf;
	}
	
	private Connection[] conns;
	
	private ConnectionFactory(int numberOfConnections) {

		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");
		
		try {
			this.conns = new Connection[numberOfConnections];
			for(int i =0; i< this.conns.length; i++) {
				Connection c = DriverManager.getConnection(url, username, password);
				this.conns[i]  = c;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.conns[0];
	}
	
	public void closeConnection() {}
}
