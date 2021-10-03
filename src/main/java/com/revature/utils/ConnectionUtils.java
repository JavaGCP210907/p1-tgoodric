package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error occurred locating PostgreSQL driver");
		}
		String gcpUrl = System.getenv("GCPURL");
		//String url = System.getenv("URL");
		String gcpPassword = System.getenv("GCPPASSWORD");
		String username = System.getenv("USERNAME");
		//String password = System.getenv("PASSWORD");
		return DriverManager.getConnection(gcpUrl, username, gcpPassword);
	}
}
