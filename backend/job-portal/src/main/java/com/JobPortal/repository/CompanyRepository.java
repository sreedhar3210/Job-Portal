package com.JobPortal.repository;

import com.JobPortal.models.Company;
import com.JobPortal.IntegrationServices.SalesforceIntegrationService;
import com.JobPortal.utils.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class CompanyRepository{

	// This method returns the Id of the company inserted in the local database.
	public static Company insertCompany(Company company) throws SQLException {
		Company insertedCompany = new Company();
		Integer rowsInserted = 0;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();

		try {
			String sqlQuery = "INSERT INTO company (company_name, industry_type, company_url, company_size, "
								+ "company_description, company_email, company_password) VALUES ('"
								+ company.getCompanyName() + "', " 
							    + company.getIndustryTypeId() + ", '" 
							    + company.getCompanyURL() + "', "
							    + company.getCompanySizeId() + ", '" 
							    + company.getCompanyDescription() + "', '" 
							    + company.getCompanyEmail() + "', '"  
							    + company.getCompanyPassword() + "')";

			rowsInserted = sqlStatement.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			Integer companyId = -1;
			if (rowsInserted > 0) {
				insertedCompany = getCompanyByEmail(company.getCompanyEmail());
			}

		} catch (SQLException sqlExc){
			System.out.println(">>> Exception in UserRepository class InsertUser method" + sqlExc);
			throw sqlExc;
		} finally{
			con.close();
		}
		return insertedCompany;
	}

	public static Company getCompanyByEmail(String email) throws SQLException {
		System.out.println(">>>> 3. getCompanyByEmail is called with email: " + email);
		Company company = null;
		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();
		String sqlQuery = "SELECT company.id, company_name, company.industry_type,"
							+ " company.company_url, company.company_size,"
							+ " company_description, company_email, company_password, "
							+ " ind.label AS industry_label, size.label AS size_label "
							+ " FROM company"
							+ " INNER JOIN select_options ind ON company.industry_type = ind.id"
							+ " INNER JOIN select_options size ON company.company_size = size.id"
							+ " WHERE company_email = '" + email + "'";
		System.out.println(">>>> sqlquery for getCompanyByEmail is " + sqlQuery);

		try{
			ResultSet rs = sqlStatement.executeQuery(sqlQuery);
			System.out.println("");
			System.out.println("");
			System.out.println(">>>>>>> rs = " + rs.toString());
			if(rs.next()){
				company = new Company(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), 
										rs.getString(6), rs.getString(7), rs.getString(8));

				System.out.println(">>>>>> 9th value is: " + rs.getString(9) + ", 10th value is: " + rs.getString(10));
				System.out.println("");
				System.out.println("");
				company.setIndustryType(rs.getString(9));
				company.setCompanySize(rs.getString(10));
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