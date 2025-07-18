//CompanyController class is used for handling the frontend Company related api calls.
package com.JobPortal.controllers;

import com.JobPortal.models.Company;
import com.JobPortal.repository.CompanyRepository;
import com.JobPortal.response.ResponseStatus;

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
public class CompanyController {

	@PostMapping("/add-company")
	public ResponseEntity<ResponseStatus> insertCompany(@RequestBody Company company) throws SQLException{
		ResponseStatus resStatus;
		try{
			CompanyRepository.insertCompany(company);
			resStatus = new ResponseStatus("CompanyInserted", "Company is Registered");
			return ResponseEntity.status(HttpStatus.OK).body(resStatus);
		} catch(SQLException sqlExc){
			System.out.println(">>>>> Exception in CompanyController class, insertCompany method: " + sqlExc);
			resStatus = new ResponseStatus("Error", "Company Insertion is failed with Error: " + sqlExc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
		}
	}

	@PostMapping("/verify-company-credentials")
    public ResponseEntity<ResponseStatus> verifyCompanyCreds(@RequestBody Company company) throws SQLException{
        ResponseStatus resStatus;
        try{
            Boolean isValidCreds = CompanyRepository.verifyCompanyCreds(company);
            if(isValidCreds)            resStatus = new ResponseStatus("Found", "Company exists");
            else                        resStatus = new ResponseStatus("NotFound", 
            											 "Company with provided credentials do not exist");
            return ResponseEntity.status(HttpStatus.OK).body(resStatus);
        } catch (SQLException sqlExc) {
            System.out.println(">>> Exception in UserController class " + sqlExc);
            resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
        }
    }
}