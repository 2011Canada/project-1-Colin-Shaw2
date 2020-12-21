package com.revature.repositories;

import java.sql.SQLException;

import com.revature.models.User;

public interface UserDAO {
	

	User getUser(String username, String password) throws SQLException;
	
}
