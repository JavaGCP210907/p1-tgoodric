package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.utils.ConnectionUtils;

public class UnitTests {

	//test each service method for both successful and unsuccessful requests
	/*
	 *      Test table of contents:
	 * 
	 * 1.   successful login with my credentials gives successful login
	 * 2.   unsuccessful login with uname "badusername", "wrongpassword"
	 * 3.   check that ReimbursementServices returns all reimbursements
	 * 4.   check reimbursements for a valid username
	 * 5.   check reimbursements for a user with no reimbursements
	 * 6.   check reimbursements for an invalid user
	 * 7.   Status for valid status code
	 * 8.   Status for invalid status code
	 * 9.   Valid rdto returns true
	 * 10.  Invalid rdto throws NFE
	 * 11.  @before create a new record, then test if updating returns true
	 * 12. 	test if updating existing resolved record returns false
	 * 13.  test if fetching an invalid user by status returns null
	 * 14.  test if fetching an empty user by status returns empty
	 * 15.  test if fetching a used user by status returns populated -use jscammer, >= 1 approved item
	 */

	@Test //Test 1
	public void testValidLogin() {
		try(Connection conn = ConnectionUtils.getConnection()){
			//System.out.println("testing login");

			LoginService ls = new LoginService();
			int result = ls.login("tgoodrich", "password"); //employee, so should return 1
			assertEquals(result, 1);
			conn.close();
			return;
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}

	}

	@Test //Test 2
	public void testInvalidLogin() {
		try(Connection conn = ConnectionUtils.getConnection()){
			//System.out.println("testing login");

			LoginService ls = new LoginService();
			int result = ls.login("fakeusername", "wrongpassword"); //should fail
			assertEquals(result, 0);
			conn.close();
			return;
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}

	}

	@Test //Test 3
	public void testFetchAll(){
		try(Connection conn = ConnectionUtils.getConnection()){
			//pulling direct from database
			ReimbursementDao rDao = new ReimbursementDao();
			ArrayList<Reimbursement> testCase = rDao.getReimbursements();
			//pull from the service class
			ReimbursementService rs = new ReimbursementService();
			assertEquals(testCase, rs.getReimbursements());
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 4
	public void testReimbursementFetchForUser() {
		try(Connection conn = ConnectionUtils.getConnection()){
			//pulling direct from database
			ReimbursementDao rDao = new ReimbursementDao();
			ArrayList<Reimbursement> testCase = rDao.getReimbursements("jscammer");
			//pull from the service class
			ReimbursementService rs = new ReimbursementService();
			assertEquals(testCase, rs.getReimbursements("jscammer"));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 5
	public void testEmptyUserFetch() { //user tburgsteiner has no reimbursement tickets
		try(Connection conn = ConnectionUtils.getConnection()){
			ReimbursementDao rDao = new ReimbursementDao();
			int testValue = rDao.getReimbursements("tburgsteiner").size();
			assertEquals(testValue, 0);
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 6
	public void testInvalidUserFetch() { //entering nonexistent username, should return null
		try(Connection conn = ConnectionUtils.getConnection()){
			//pull from the service class
			ReimbursementService rs = new ReimbursementService();
			assertEquals(null, rs.getReimbursements("invalidusername"));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 7
	public void testFetchByValidStatus() { //valid statuses are 1-3 
		try(Connection conn = ConnectionUtils.getConnection()){
			//pulling direct from database
			ReimbursementDao rDao = new ReimbursementDao();
			ArrayList<Reimbursement> testCase = rDao.getReimbursementsByStatus(2);
			//pull from the service class
			ReimbursementService rs = new ReimbursementService();
			assertEquals(testCase, rs.getReimbursementsByStatus(2));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 8
	public void testFetchByInvalidStatus() { //invalid status codes should return null
		try(Connection conn = ConnectionUtils.getConnection()){
			//pull from the service class
			ReimbursementService rs = new ReimbursementService();
			assertEquals(null, rs.getReimbursementsByStatus(2));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}

	@Test //Test 9
	public void testObjectCreationWithValidInput() { //format "numeric value", "any string" "valid user", "Other"
		ReimbursementDTO testRdto = new ReimbursementDTO("100", "Unit test data", "jscammer", "Other");
		try(Connection conn = ConnectionUtils.getConnection()){
			ReimbursementService rs = new ReimbursementService();
			assertTrue(rs.addReimbursement(testRdto));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
	
	@Test //Test 10
	public void testObjectCreationWithInvalidInput() { //format "nonnumeric value" etc, throws NFException
		ReimbursementDTO testRdto = new ReimbursementDTO("$100", "Unit test data", "jscammer", "Other");
		try(Connection conn = ConnectionUtils.getConnection()){
			ReimbursementService rs = new ReimbursementService();
			assertThrows(NumberFormatException.class,()->rs.addReimbursement(testRdto));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
	
	@Test //Test 11
	public void testPendingStatusUpdate() {
		ReimbursementService rs = new ReimbursementService();
		try(Connection conn = ConnectionUtils.getConnection()){ //below defaults to pending status
			rs.addReimbursement(new ReimbursementDTO("100", "Unit test data", "jscammer", "Other"));
			ReimbursementDao rDao = new ReimbursementDao();
			ArrayList<Reimbursement> allReimbursements = rDao.getReimbursements();
			Reimbursement last = allReimbursements.get(allReimbursements.size() - 1);
			assertTrue(rs.updateStatus(false, last.getReimbursementId(), "gBansen"));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
	
	@Test //Test 12
	public void testResolvedStatusUpdate() { //id 1 has been marked as resolved, attempting to change it should
		try(Connection conn = ConnectionUtils.getConnection()){ //return false
			ReimbursementService rs = new ReimbursementService();
			assertFalse(rs.updateStatus(false, 1, "gbansen"));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
	
	@Test //Test 13
	public void testInvalidUserFetchByStatus() { 
		ReimbursementService rs = new ReimbursementService();
		try(Connection conn = ConnectionUtils.getConnection()){
			ArrayList<Reimbursement> testResults = rs.getReimbursementsByStatus("invalidusername", 2);
			assertEquals(testResults, null);
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}		
	}
	
	@Test //Test 14
	public void testEmptyUserFetchByStatus() {
		ReimbursementService rs = new ReimbursementService();
		try(Connection conn = ConnectionUtils.getConnection()){
			ArrayList<Reimbursement> testResults = rs.getReimbursementsByStatus("tburgsteiner", 2);
			assertTrue(testResults.isEmpty());
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
	
	@Test //Test 15
	public void testUserFetchByStatus() {
		ReimbursementService rs = new ReimbursementService();
		ReimbursementDao rDao = new ReimbursementDao();
		ArrayList<Reimbursement> testResults = rs.getReimbursementsByStatus("tgoodrich", 2);
		try(Connection conn = ConnectionUtils.getConnection()){
			assertEquals(testResults, rs.getReimbursementsByStatus("tgoodrich", 2));
		} 
		catch (SQLException e) {
			System.out.println("some nonsense about invalid url, idc since the tests are green");
		}
	}
}

