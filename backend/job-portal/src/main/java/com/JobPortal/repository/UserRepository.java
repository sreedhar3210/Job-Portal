package com.JobPortal.repository;

import com.JobPortal.models.User;
import com.JobPortal.utils.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class UserRepository{

	//using a static id which is added every time a record is created, to maintain id.

	public static Integer insertUser(User user) throws SQLException {
		Integer rowsInserted = 0, newId;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();

		try{
			newId = getUserSize() + 1;

			User userWithSameEmail = getUserByEmail(user.getEmail());

			if(userWithSameEmail != null){
				rowsInserted = 0;
			}
			else{
				//you need to enclose the variables inside single quotes in the query.
				String sqlQuery = "INSERT INTO user (id, firstname, lastname, email, password) VALUES (" 
								    + newId + ", '" 
								    + user.getFirstname() + "', '" 
								    + user.getLastname() + "', '" 
								    + user.getEmail() + "', '" 
								    + user.getPassword() + "')";

				rowsInserted = sqlStatement.executeUpdate(sqlQuery);
			}
			System.out.println(">>>> " + rowsInserted + "Rows are inserted");
		} catch (SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class InsertUser method" + sqlExc);
			throw sqlExc;
		} finally{
			con.close();
		}
		return rowsInserted;
	}

	public static Integer getUserSize() throws SQLException {
		Integer numberOfUsers = 0;	
		String sqlQuery = "SELECT COUNT(id) FROM user";
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();

		try (
	        ResultSet rs = sqlStatement.executeQuery(sqlQuery)) {

			//When you execute a SELECT query in JDBC, it returns a ResultSet — a table-like structure containing the rows returned from the database.
	        if (rs.next()) {
	        	//rs.next() -> will make the rs to be in the next row ( currently first row in this case )
	            numberOfUsers = rs.getInt(1); // gets the count from first column
	        }
	    } catch(SQLException sqlExc){
	    	System.out.println(">>> Exceptino in UserRepository class GetUserSize method" + sqlExc);
	    	throw sqlExc;
	    } finally{
	    	con.close();
	    }

	    return numberOfUsers;
	}

	public static User getUserByEmail(String email) throws SQLException {
		String firstname = "";
		String lastname = "";
		String password = "";
		User user = null;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();
		String sqlQuery = "SELECT firstname, lastname, email, password "
							+ "FROM user "
							+ "WHERE email = '" + email + "'";
		System.out.println(">>>> sqlquery for getUserbyEmail is " + sqlQuery);

		try{
			ResultSet rs = sqlStatement.executeQuery(sqlQuery);

			if(rs.next()){
				firstname = rs.getString(1);
				lastname = rs.getString(2);
				email = rs.getString(3);
				password = rs.getString(4);
				user = new User(firstname, lastname, email, password);
			}
		} catch(SQLException sqlExc){
	    	System.out.println(">>> Exceptino in UserRepository class GetUserSize method" + sqlExc);
	    	throw sqlExc;
	    } finally{
	    	con.close();
	    }
	    return user;
	}

	// public static Boolean verifyUserCreds(User user) throws SQLException {
	// 	String sqlQuery = "SELECT id, firstname, lastname "
	// 					+ "FROM user "
	// 					+ "WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "';";


	// 	try{
	// 		Statement sqlStatement = con.createStatement();

	// 	}
	// }
}