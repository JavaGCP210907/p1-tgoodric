/**
 * 
 */
package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtils;

/**
 * @author T.D. Goodrich
 *
 */
public class UserDao implements IUserDao {

	/**
	 * 
	 */
	public UserDao() {
		// No implementation
	}


	@Override
	public ArrayList<User> getUsers() throws SQLException {

		String sql = "select * from users";

		ArrayList<User> users = null;
		try(Connection conn = ConnectionUtils.getConnection()){
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			users = generateResults(rs);
		}
		catch(SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while retrieving all users from table users: \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString(), e);
		}
		return users;
	}

	/**
	 * Generic method used internally to generate a set of Users to be returned
	 * 
	 * @param rs
	 * @return ArrayList<User> The User objects matching the SQL query
	 * @throws SQLException
	 */
	private ArrayList<User> generateResults(ResultSet rs) throws SQLException {
		ArrayList<User> users;
		users = new ArrayList<>();
		while(rs.next()) {
			User u = new User(
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("password_hash"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("email"),
					rs.getInt("role_id_fk")
					);
			users.add(u);
		}
		return users;
	}

	/**
	 * Returns an ArrayList<User> (of size 1) matching the userId passed
	 * 
	 * @param int userId the userId being searched for
	 * @return ArrayList<User> the Users matching the ID passed
	 * @throws SQLException
	 */
	@Override
	public ArrayList<User> getUsers(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Returns an ArrayList<User> (of size 1) matching the username passed
	 * 
	 * @param String username The username being searched for
	 * @return ArrayList<User> An ArrayList containing the matching users
	 * @throws SQLException
	 */
	@Override
	public ArrayList<User> getUsers(String username) throws SQLException {

		String sql = "select * from users where username = ?";
		ResultSet rs = null;
		ArrayList<User> users = null;
		try(Connection conn = ConnectionUtils.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			rs = ps.executeQuery();
			users = generateResults(rs);
			return users;
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while retrieving users by username: \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString(), e);
		}
	}

	@Override
	public ArrayList<User> getUsers(String fName, String lName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getUsers(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(int userId, User updated) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public ArrayList<User> getForLogin(String username, String password) throws SQLException {
		String sql = "select * from users where username = ? and password_hash = ?";
		ResultSet rs = null;
		ArrayList<User> result = null;
		try(Connection conn = ConnectionUtils.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			result = generateResults(rs);
			return result;
		}
		catch(SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while attempting to log in: \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString(), e);
		}
	}

}
