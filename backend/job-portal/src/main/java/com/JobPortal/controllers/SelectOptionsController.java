package com.JobPortal.controllers;

import com.JobPortal.models.SelectOptions;
import com.JobPortal.models.OptionCategory;
import com.JobPortal.repository.SelectOptionsRepository;
import com.JobPortal.response.ResponseStatus;

import java.util.List;
import java.util.Map;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class SelectOptionsController {

	@PostMapping("/get-select-options")
	public ResponseEntity<ResponseStatus<Map<String, List<SelectOptions>>>> 
		getSelectOptions(@RequestBody List<String> optionCategoryNameList) throws SQLException{
		
		Map<String, List<SelectOptions>> selectOptionsByCategoryMap;
		ResponseStatus<Map<String, List<SelectOptions>>> resStatus;
		try{
			selectOptionsByCategoryMap = SelectOptionsRepository.getSelectOptions(optionCategoryNameList);
			resStatus = new ResponseStatus("OK", "Succesfully Fetched SelectOptions",
											selectOptionsByCategoryMap);
			return ResponseEntity.status(HttpStatus.OK).body(resStatus);
		} catch(SQLException sqlExc){
			System.out.println(">>>>> Exception in SelectOptionsController class, getSelectOptions method: " + sqlExc);
			resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
		}
		
	}
}