//class used for sql operations on Job Model.
package com.JobPortal.repository;

import com.JobPortal.models.Job;
import com.JobPortal.utils.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class JobRepository{
  	public static Integer insertJob(Job job) throws SQLException {
        System.out.println(">>>> inside Job Repository class");
      	Integer rowsInserted = 0;
      	Connection con = DBConnection.getConnection();
      	Statement sqlStatement = con.createStatement();
      	
      	try{
          	String sqlQuery = "INSERT INTO Job (role_id, description, responsibilities, requirements, "
              									+ "location_id, employement_type_id, experience_level_id, "
          										+ "salary, company_id, posted_date, last_date) VALUES ("
              									+ job.getRoleId() + ", '"
              									+ job.getDescription() + "', '"
              									+ job.getResponsibilities() + "', '"
              									+ job.getRequirements() + "', "
              									+ job.getLocationId() + ", "
              									+ job.getEmploymentTypeId() + ", "
              									+ job.getExperienceLevelId() + ", "
              									+ job.getSalary() + ", "
              									+ job.getCompanyId() + ", "
              									+ job.getPostedDate() + ", "
              									+ job.getLastDate()+ ")";
          rowsInserted = sqlStatement.executeUpdate(sqlQuery);
          System.out.println(">>>>>> " + rowsInserted +" rows inserted");
        } catch(SQLException sqlExc){
          	System.out.println(">>>> Exception in JobRepository class, insertJob method " + sqlExc);
          	throw sqlExc;
        } finally{
          	con.close();
        }
      
      	return rowsInserted;
    }
}