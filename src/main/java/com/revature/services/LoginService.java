package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class LoginService {
	
	//select user name and password
	
	public int login(String username, String password) {
		//System.out.println(username + password);
		UserDao uDao = new UserDao();
		
		ArrayList<User> match = null;
		//Because there is a unique constraint on username field in DB, the length is at most 1;
		try {
			match = uDao.getForLogin(username, password);
			//System.out.println(match);
		} 
		catch (SQLException e) { //precaution, DB errors return 0
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}
		
		if (match.size() != 1) {//> 1 impossible due to database constraints, but 
			return 0;			//guarding against it anyways
		}
		return match.get(0).getRoleId(); 
	}
	
}
