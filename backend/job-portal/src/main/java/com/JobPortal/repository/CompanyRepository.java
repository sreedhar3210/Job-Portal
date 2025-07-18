package com.JobPortal.repository;

import com.JobPortal.models.Company;
import com.JobPortal.utils.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class CompanyRepository{

	public static Integer insertCompany(Company company) throws SQLException {
		Integer rowsInserted = 0;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();

		try{
			String sqlQuery = "INSERT INTO company (company_name, industry_type, company_url, company_size, "
								+ "company_description, company_email, company_password) VALUES ('"
								+ company.getCompanyName() + "', '" 
							    + company.getIndustryType() + "', '" 
							    + company.getCompanyURL() + "', '"
							    + company.getCompanySize() + "', '" 
							    + company.getCompanyDescription() + "', '" 
							    + company.getCompanyEmail() + "', '"  
							    + company.getCompanyPassword() + "')";

			rowsInserted = sqlStatement.executeUpdate(sqlQuery);
			System.out.println(">>>> " + rowsInserted + "Rows are inserted");
		} catch (SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class InsertUser method" + sqlExc);
			throw sqlExc;
		} finally{
			con.close();
		}
		return rowsInserted;
	}

	public static Company getCompanyByEmail(String email) throws SQLException {
		// System.out.println(">>>> getCompanyByEmail is called with email: " + email);
		Company company = null;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();
		String sqlQuery = "SELECT id, company_name, industry_type, company_url, company_size, "
							+ " company_description, company_email, company_password "
							+ "FROM company "
							+ "WHERE company_email = '" + email + "'";
		// System.out.println(">>>> sqlquery for getCompanyByEmail is " + sqlQuery);

		try{
			ResultSet rs = sqlStatement.executeQuery(sqlQuery);

			if(rs.next()){
				company = new Company(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), 
										rs.getString(6), rs.getString(7), rs.getString(8));
			}
		} catch(SQLException sqlExc){
	    	System.out.println(">>> Exceptino in UserRepository class GetUserSize method" + sqlExc);
	    	throw sqlExc;
	    } finally{
	    	con.close();
	    }
	    return company;
	}

	public static Boolean verifyCompanyCreds(Company company) throws SQLException {

		Boolean isValidCreds = false;
		Company dbCompany = null;
		try{
			dbCompany = getCompanyByEmail(company.getCompanyEmail());
			// dbCompany.displayCompanyValues();
		} catch(SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class, verifyUserCreds method " + sqlExc);
			throw sqlExc;
		}
		//In java string1 == string2 doesn't work as strings are compared to references in this case
		//so we need to use the string.equals method.
		if(dbCompany != null && dbCompany.getCompanyPassword().equals(company.getCompanyPassword())){
			System.out.println(">>> company is verified correctly, inside if loop");
			isValidCreds = true;
		}		
		return isValidCreds;
	}
}