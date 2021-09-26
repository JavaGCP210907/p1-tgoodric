package com.revature.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Role;
import com.revature.models.User;

public interface IUserDao {
	//TODO: design interface for users, may need more overloads on getUsers
	public ArrayList<User> getUsers() throws SQLException;
	public ArrayList<User> getUsers(int userId) throws SQLException; //search on unique private key
	public ArrayList<User> getUsers(String username) throws SQLException;
	public ArrayList<User> getUsers(String fName, String lName) throws SQLException;
	public ArrayList<User> getUsers(Role role) throws SQLException;
	public boolean createUser(User user) throws SQLException;
	public boolean deactivateUser(int userId) throws SQLException; //users never deleted for record-keeping purposes
	public boolean updateUser(int userId, User updated) throws SQLException; //TODO:may need to change this one
}
