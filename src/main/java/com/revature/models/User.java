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
	 * @param userId
	 * @param username
	 * @param passwordHash
	 * @param fName
	 * @param lName
	 * @param email
	 * @param roleId
	 */
	public User(int userId, String username, String passwordHash, String fName, String lName, String email,
			int roleId) {
		super();
		this.userId = userId;
		this.username = username;
		this.passwordHash = passwordHash;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleId = roleId;
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



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwordHash=" + passwordHash + ", fName="
				+ fName + ", lName=" + lName + ", email=" + email + ", roleId=" + roleId + "]";
	}



	public int getUserId() {
		return userId;
	}



	public String getUsername() {
		return username;
	}



	public String getPasswordHash() {
		return passwordHash;
	}



	public String getfName() {
		return fName;
	}



	public String getlName() {
		return lName;
	}



	public String getEmail() {
		return email;
	}



	public int getRoleId() {
		return roleId;
	}



	void setUserId(int userId) {
		this.userId = userId;
	}



	void setUsername(String username) {
		this.username = username;
	}



	void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}



	void setfName(String fName) {
		this.fName = fName;
	}



	void setlName(String lName) {
		this.lName = lName;
	}



	void setEmail(String email) {
		this.email = email;
	}



	void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
