package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class LoginService {
	
	//select user name and password
	
	public boolean login(String username, String password) {
		System.out.println(username + password);
		UserDao uDao = new UserDao();
		
		ArrayList<User> match = null;
		//Because there is a unique constraint on username field in DB, the length is at most 1;
		try {
			match = uDao.getForLogin(username, password);
			System.out.println(match);
		} 
		catch (SQLException e) { //TODO: figure out how to properly handle this
			e.printStackTrace();
			return false;
		}
		
		if (match.size() != 1) {
			return false;
		}
		
		return true;
	}
	
}
