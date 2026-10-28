//CompanyController class is used for handling the frontend Company related api calls.
package com.JobPortal.controllers;

import com.JobPortal.models.Company;
import com.JobPortal.models.TransactionLog;
import com.JobPortal.repository.CompanyRepository;
import com.JobPortal.repository.TransactionLogRepository;
import com.JobPortal.response.ResponseStatus;
import com.JobPortal.response.LoginResponse;
import com.JobPortal.IntegrationServices.SalesforceIntegrationService;

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

    private final TransactionLogRepository logRepo;
    private final SalesforceIntegrationService salesforceService;

    //This constructor is automatically handled by spring boot as this class has RestController annotation.
    public CompanyController(TransactionLogRepository logRepo,
                                SalesforceIntegrationService salesforceService) {

        this.logRepo = logRepo;
        this.salesforceService = salesforceService;
    }

	@PostMapping("/add-company")
	public ResponseEntity<ResponseStatus> insertCompany(@RequestBody Company company) throws SQLException{
		ResponseStatus resStatus;
        String salesforceAccountId;

		try{
            Company insertedCompany = null;

            try {
                insertedCompany = CompanyRepository.insertCompany(company);
            } catch(Exception exc) {
                throw exc;
            }

            TransactionLog trxnLogSavedtoDB = new TransactionLog(
                "MYSQL Local database",
                "Insert",
                (insertedCompany.getId() != null ? "SUCCESS" : "FAILURE"),
                (insertedCompany.getId() != null ? insertedCompany.getId() : "-1").toString(),
                "Company record is saved to local database succesfully"
            );

            logRepo.saveTrxnLog(trxnLogSavedtoDB);

            company.setId(insertedCompany.getId());

			resStatus = new ResponseStatus("CompanyInserted", "Company is Registered");

            // System.out.println(">>>>> 1. after inserting company in the local database");
            try{
                salesforceAccountId = salesforceService.createSalesforceAccount(insertedCompany);
            } catch(Exception exc) {
                throw exc;
            }

            TransactionLog trxnLogSavedtoSalesforce = new TransactionLog(
                "Salesforce Integration",
                "Insert",
                "SUCCESS",
                salesforceAccountId,
                "Company record is saved to local database succesfully"
            );

            logRepo.saveTrxnLog(trxnLogSavedtoSalesforce);

			return ResponseEntity.status(HttpStatus.OK).body(resStatus);
		} catch(SQLException sqlExc){
			System.out.println(">>>>> Exception in CompanyController class, insertCompany method: " + sqlExc);
			resStatus = new ResponseStatus("Error", "Company Insertion is failed with Error: " + sqlExc.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
		}
	}

	// @PostMapping("/verify-company-credentials")
    // public ResponseEntity<ResponseStatus> verifyCompanyCreds(@RequestBody Company company) throws SQLException{
    //     ResponseStatus resStatus;
    //     try{
    //         Boolean isValidCreds = CompanyRepository.verifyCompanyCreds(company);
    //         if(isValidCreds)            resStatus = new ResponseStatus("Found", "Company exists");
    //         else                        resStatus = new ResponseStatus("NotFound", 
    //         											 "Company with provided credentials do not exist");
    //         return ResponseEntity.status(HttpStatus.OK).body(resStatus);
    //     } catch (SQLException sqlExc) {
    //         System.out.println(">>> Exception in UserController class " + sqlExc);
    //         resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
    //     }
    // }

	@PostMapping("/verify-company-credentials")
    public ResponseEntity<LoginResponse> verifyCompanyCreds(@RequestBody Company company) throws SQLException{
		String companyEmail = company.getCompanyEmail();
		System.out.println(">>>>>> 1. inside verifyCompanyCreds companyEmail is: " + companyEmail);
        LoginResponse loginResponse;
        try{
            Boolean isValidCreds = CompanyRepository.verifyCompanyCreds(company);
			Company companyDetails = CompanyRepository.getCompanyByEmail(companyEmail);
			String status = isValidCreds ? "Found" : "NotFound";
			String message = isValidCreds ? "Company exists" : "Company with provided credentials do not exist";
			loginResponse = new LoginResponse(false, true, status, message, null, companyDetails);
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
        } catch (SQLException sqlExc) {
            System.out.println(">>> Exception in UserController class " + sqlExc);
			String status = "Error";
			String message = "User Insertion is failed with Error: " + sqlExc.getMessage();
			loginResponse = new LoginResponse(false, true, status, message, null, null);
            // resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponse);
        }
    }
}