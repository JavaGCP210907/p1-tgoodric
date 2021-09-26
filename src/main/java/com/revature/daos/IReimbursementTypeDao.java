package com.revature.daos;

import java.sql.SQLException;

public interface IReimbursementTypeDao {
	
	//Finalized 26/09/2021
	public String getType(int typeId) throws SQLException;

}
