package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Role;
import com.revature.models.User;

public interface IUserDao {
	//TODO: design interface for users, may need more overloads on getUsers
	public ArrayList<User> getUsers();
	public ArrayList<User> getUsers(int userId);
	public ArrayList<User> getUsers(String username);
	public ArrayList<User> getUsers(String fName, String lName);
	public ArrayList<User> getUsers(Role role);
	public boolean createUser(User user);
	public boolean deactivateUser(int userId); //users never deleted for record-keeping purposes
	public boolean updateUser(int userId, User updated); //TODO:may need to change this one
}
