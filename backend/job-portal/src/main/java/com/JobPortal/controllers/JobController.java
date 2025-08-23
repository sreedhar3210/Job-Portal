//JobController class is used for handling the frontend job related api calls.
package com.JobPortal.controllers;

import com.JobPortal.models.Job;
import com.JobPortal.repository.JobRepository;
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
public class JobController {
  	
  	@PostMapping("/post-job")
  	public ResponseEntity<ResponseStatus> postJob(@RequestBody String jobString) throws SQLException{
        System.out.println(">>>> Inside JobController class");
        System.out.println(">>>> jobString= " + jobString);
        Job job = new Job();
      	ResponseStatus resStatus;
      	try{
          	JobRepository.insertJob(job);
          	resStatus = new ResponseStatus("OK", "Job is posted");
          	return ResponseEntity.status(HttpStatus.OK).body(resStatus);
        } catch(SQLException sqlExc){
          	System.out.println(">>>>> Exception in JobController class, postJob method: " + sqlExc);
          	resStatus = new ResponseStatus("Error", "Job Posting is failed with Error: " + sqlExc.getMessage());
          	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
        }
    }
}