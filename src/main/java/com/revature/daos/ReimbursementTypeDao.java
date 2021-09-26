package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.utils.ConnectionUtils;

public class ReimbursementTypeDao implements IReimbursementTypeDao {

	public ReimbursementTypeDao() {
		// No implementation
	}

	@Override
	public String getType(int typeId) throws SQLException {
		try (Connection conn = ConnectionUtils.getConnection()){

			String sql = "select * from roles where role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			return rs.getString("type_name");
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error accessing table \"reimbursement_types\" \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append(", Vendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString());
		}
	}
}
