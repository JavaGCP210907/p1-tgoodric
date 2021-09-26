package com.revature.models;

public class User {
	int userId; //primary key, serial
	String username; //text
	String passwordHash; //text
	String fName; //text
	String lName; //text
	String email; //text
	int roleId; //FK, keyed to role

	//ctors
	public User() {
		super();
		//no further implementation, not used
	}



	/**
	 * @param username User-selected username, must be unique
	 * @param passwordHash 
	 * @param fName
	 * @param lName
	 * @param email
	 * @param roleId
	 */
	public User(String username, String passwordHash, String fName, String lName, String email, int roleId) {
		super();
		this.username = username;
		this.passwordHash = passwordHash; //hashed before being passed to ctor
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleId = roleId;
	}
}
