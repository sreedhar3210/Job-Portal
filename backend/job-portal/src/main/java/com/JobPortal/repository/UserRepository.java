package com.JobPortal.repository;

import com.JobPortal.models.User;
import com.JobPortal.utils.DBConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UserRepository{

	//using a static id which is added every time a record is created, to maintain id.
	private static Integer id = 1;

	public static void insertUser(User user) throws SQLException {
		try{
			
			Connection con = DBConnection.getConnection();

			//you need to enclose the firstname and lastname variables inside single quotes in the query.
			String sqlQuery = "INSERT INTO user (id, firstname, lastname) VALUES (" + id + ", '" + user.getFirstname() + "', '" + user.getLastname() + "')";
			id++;		//incrementing the id.

			System.out.println(">>> the sqlquery is " + sqlQuery);

			Statement sqlStatement = con.createStatement();
			int rowsInserted = sqlStatement.executeUpdate(sqlQuery);

		} catch (SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class " + sqlExc);
			throw sqlExc;
		}
	}
}