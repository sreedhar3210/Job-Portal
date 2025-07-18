package com.JobPortal.repository;

import com.JobPortal.models.OptionCategory;
import com.JobPortal.models.SelectOptions;
import com.JobPortal.utils.DBConnection;
import com.JobPortal.repository.OptionCategoryRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class SelectOptionsRepository {
	public static Map<String,List<SelectOptions>> getSelectOptions(List<String> categoryNameList) throws SQLException{
		//Map<CategoryName, List<SelectOptions> with that category>
		Map<String, List<SelectOptions>> selectOptionsByCategoryMap = new HashMap<String, List<SelectOptions>>();
		//using map to get the category name by id
		Map<Integer, String> optionCategoryIdNameMap = new HashMap<Integer, String>();
		try{
			List<OptionCategory> optionCategoryList = OptionCategoryRepository.getOptionCategories(categoryNameList);
			Integer i, n = optionCategoryList.size();
			String inClause = "(";
			//constructing the inClause String.
			for(i=0;i<n;i++){
				inClause += optionCategoryList.get(i).getId();
				if(i+1 < n)		inClause+=",";
				//constructing the optionCategoryIdNameMap
				optionCategoryIdNameMap.put(optionCategoryList.get(i).getId(), optionCategoryList.get(i).getName());
			}
			inClause+=")";
			String sqlQuery = "SELECT id, label, value, category_id "
								+ "FROM select_options "
								+ "WHERE category_id IN " + inClause + ";";
			Connection con = DBConnection.getConnection();
			Statement sqlStatement = con.createStatement();

			try{
				ResultSet rs = sqlStatement.executeQuery(sqlQuery);
				while(rs.next()){
					Integer id = rs.getInt(1);
					String label = rs.getString(2);
					String value = rs.getString(3);
					Integer categoryId = rs.getInt(4);
					SelectOptions selectOptions = new SelectOptions(id, label, value, categoryId);
					//fetching the categoryName from the map we constructed before.
					String categoryName = optionCategoryIdNameMap.get(categoryId);
					//adding this selectOptions to corresponding CategoryName's SelectOptions List.
					// here k -> new ArrayList is a lambda function similar to javascript.
					selectOptionsByCategoryMap
						.computeIfAbsent(categoryName, k -> new ArrayList<SelectOptions>())
						.add(selectOptions);
				}
			} catch(SQLException sqlExc){
				System.out.println(">>> Error while fetching the Select Options: " + sqlExc);
				throw sqlExc;
			} finally{
				con.close();
			}
		} catch(SQLException sqlExc){
			System.out.println(">>>> Error in SelectOptions Repository class getSelectOptions method: " + sqlExc);
			throw sqlExc;
		}
		return selectOptionsByCategoryMap;
	}
}