/**
 * 
 */
package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtils;

/**
 * @author T. D. Goodrich
 *
 */
public class ReimbursementDao implements IReimbursementDao {

	/**
	 * Default Ctor, no implementation
	 */
	public ReimbursementDao() {
		super();
		//no implementation
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements() throws SQLException {
		String sql = "select * from reimbursements";
		ArrayList<Reimbursement> reimbursements = null;
		try(Connection conn = ConnectionUtils.getConnection()){
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			reimbursements = generateResults(rs);
			return reimbursements;
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while retrieving all reimbursements: \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString());
		}
	}

	private ArrayList<Reimbursement> generateResults(ResultSet rs) throws SQLException {
		ArrayList<Reimbursement> reimbursements;
		reimbursements = new ArrayList<Reimbursement>();
		while(rs.next()) {
			Reimbursement r = new Reimbursement(
					rs.getInt("reimbursement_id"),
					rs.getDouble("amount"),
					rs.getDate("submitted"),
					rs.getDate("resolved"),
					rs.getString("description"),
					null, //TODO: figure out how to translate from BYTEA to Image
					rs.getInt("submitter_id_fk"),
					rs.getInt("resolver_id_fk"),
					rs.getInt("reimbursement_type_fk"),
					rs.getInt("reimbursement_status_fk"));
			reimbursements.add(r);

		}
		return reimbursements;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(int id) throws SQLException {
		String sql = "select * from reimbursements where reimbursement_id = ?";
		ArrayList<Reimbursement> reimbursements = null;
		try(Connection conn = ConnectionUtils.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			reimbursements = generateResults(rs);
			return reimbursements;
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while retrieving all reimbursements: \nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString());
		}
	}

	/**
	 * @param id
	 * @param newStatus
	 * @param resolverId
	 * @return
	 * @throws SQLException
	 */
	public boolean updateStatus(int id, int newStatus, int resolverId) throws SQLException{
		String sql = "update reimbursements " +
				"set reimbursement_status_fk = ?, resolver_id_fk = ?, resolved = ?" +
				"where reimbursement_id = ?";
		try(Connection conn = ConnectionUtils.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, newStatus);
			ps.setInt(2, resolverId);
			ps.setDate(3, new Date(System.currentTimeMillis()));
			ps.setInt(4, id);
			Reimbursement r = getReimbursements(id).get(0);
			ps.execute(); //forgot this, was stumped as to why my updates were failing
			//System.out.println(getReimbursements(id).get(0).equals(r));
		}
		catch(SQLException e) {
			System.out.println("SQLException error code: " + e.getSQLState());
			throw new SQLException("SQLException error code: " + e.getSQLState(),e);
			//return false;
		}
		return true;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(String username) throws SQLException {
		//System.out.println(username);
		String sql = "select * from reimbursements where submitter_id_fk = ?";
		//String sql = "select * from reimbursements inner join users " +
		//		"on users.user_id = reimbursements.submitter_id_fk " +
		//		"where username = ?"; //god I hate writing SQL queries
		UserDao uDao = new UserDao();
		int id = uDao.getUserId(username); 	//it's a workaround, and I don't like it
		ResultSet rs = null;				
		ArrayList<Reimbursement> results = null;
		try(Connection conn = ConnectionUtils.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//System.out.println(username);
			//ps.setString(1, username);

			rs = ps.executeQuery();
			//System.out.println("fetching results");
			results = generateResults(rs);
			//System.out.println(results);
			return results;
		}
		catch(SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while retrieving reimbursements for ");
			sb.append(username);
			sb.append("\nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			//System.out.println(sb);
			throw new SQLException(sb.toString());
		}
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(String fName, String lName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsBySubmitterId(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsbyResolverId(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) throws SQLException {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO reimbursements(amount, submitted, resolved, " +
					"description, receipt, submitter_id_fk, resolver_id_fk, " +
					"reimbursement_type_fk,reimbursement_status_fk)" +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, r.getAmount());
			ps.setDate(2, r.getSubmitted());
			ps.setDate(3, r.getResolved());
			ps.setString(4, r.getDescription());
			ps.setBytes(5, null);
			ps.setInt(6, r.getSubmittedBy()); 
			ps.setInt(7,4);//4 is a userId to mark no resolver, added after test users
			ps.setInt(8, r.getReimbursementType());
			ps.setInt(9, r.getStatus());
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			StringBuilder sb = new StringBuilder("Error occurred while adding reimbursement: ");
			sb.append("\nSQL State: ");
			sb.append(e.getSQLState());
			sb.append("\nVendor Error Code: ");
			sb.append(e.getErrorCode());
			throw new SQLException(sb.toString(),e);
		}
	}

}
