package com.revature.daos;

import java.sql.SQLException;

public interface IRoleDao {
	
	public String getRole(int id) throws SQLException;

}
