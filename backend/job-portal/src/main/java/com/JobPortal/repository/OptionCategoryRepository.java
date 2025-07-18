package com.JobPortal.repository;

import com.JobPortal.models.OptionCategory;
import com.JobPortal.repository.OptionCategoryRepository;
import com.JobPortal.utils.DBConnection;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class OptionCategoryRepository {

	public static List<OptionCategory> getOptionCategories(List<String> optionCategoryNameList) throws SQLException{
		Integer i, n = optionCategoryNameList.size();
		String inClause = "('";
		for(i=0;i<n;i++){
			inClause += (optionCategoryNameList.get(i) + "'");
			if(i+1 < n)		inClause += ",'";
		}
		inClause+=")";
		String sqlQuery = "SELECT id, name "
							+ "FROM option_category "
							+ "WHERE name IN " + inClause + ";";

		Connection con = DBConnection.getConnection();
		Statement sqlStatement = con.createStatement();
		List<OptionCategory> optionCategoryIdNameList = new ArrayList<OptionCategory>();
		try{
			ResultSet rs = sqlStatement.executeQuery(sqlQuery);

			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				OptionCategory oc = new OptionCategory(id, name);
				optionCategoryIdNameList.add(oc);
			}
		} catch(SQLException sqlExc){
			System.out.println(">>>> In OptionCategoryRepository class exception is " + sqlExc);
			throw sqlExc;
		} finally{
			con.close();
		}
		return optionCategoryIdNameList;
	}
}