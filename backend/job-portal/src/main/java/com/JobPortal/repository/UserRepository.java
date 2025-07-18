package com.JobPortal.repository;

import com.JobPortal.models.User;
import com.JobPortal.utils.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class UserRepository{

	public static Integer insertUser(User user) throws SQLException {
		Integer rowsInserted = 0;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();

		try{
			User userWithSameEmail = getUserByEmail(user.getEmail());

			if(userWithSameEmail != null){
				rowsInserted = 0;
			}
			else{
				//you need to enclose the variables inside single quotes in the query.
				String sqlQuery = "INSERT INTO user (firstname, lastname, email, password) VALUES ('"
								    + user.getFirstname() + "', '" 
								    + user.getLastname() + "', '" 
								    + user.getEmail() + "', '" 
								    + user.getPassword() + "')";

				rowsInserted = sqlStatement.executeUpdate(sqlQuery);
			}
			System.out.println(">>>> " + rowsInserted + " Rows are inserted");
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

	public static Boolean verifyUserCreds(User user) throws SQLException {
		Boolean isValidCreds = false;
		User dbUser = null;
		try{
			dbUser = getUserByEmail(user.getEmail());
		} catch(SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class, verifyUserCreds method " + sqlExc);
			throw sqlExc;
		}
		System.out.println(">>> dbUser= " + dbUser);
		System.out.println(">>> dbUser's password is " + dbUser.getPassword() + " users password = " + user.getPassword());
		//In java string1 == string2 doesn't work as strings are compared to references in this case
		//so we need to use the string.equals method.
		if(dbUser != null && dbUser.getPassword().equals(user.getPassword())){
			System.out.println(">>> user is verified correctly, inside if loop");
			isValidCreds = true;
		}		
		return isValidCreds;
	}
}