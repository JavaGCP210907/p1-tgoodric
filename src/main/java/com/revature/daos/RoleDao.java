package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.utils.ConnectionUtils;

/**
 * @author T.D. Goodrich
 * Completed 26/09/2021
 */
public class RoleDao implements IRoleDao {

	/**
	 * Why are you reading this?
	 */
	public RoleDao() {
		// No further implementation
	}
	
	/**
	 * @param id int 
	 */
	@Override
	public String getRole(int id) throws SQLException {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "select * from roles where role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			return rs.getString("role_name");
			
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error accessing table \"roles\" \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append(", Vendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString());
		}
	}

}
