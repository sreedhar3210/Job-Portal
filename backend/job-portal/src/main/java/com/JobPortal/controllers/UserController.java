//UserController class is used for handling the frontend User related api calls.
package com.JobPortal.controllers;

import com.JobPortal.models.User;
import com.JobPortal.repository.UserRepository;
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
@CrossOrigin(origins = {"http://localhost:3000"}) // Allowing localhost:3000 apis
public class UserController {

    @PostMapping("/add-user")
    public ResponseEntity<ResponseStatus> insertUser(@RequestBody User user) throws SQLException{
        ResponseStatus resStatus;
        try {
            UserRepository.insertUser(user);
        	resStatus = new ResponseStatus("UserInserted", "User is registered");
            return ResponseEntity.status(HttpStatus.OK).body(resStatus);
        } catch (SQLException sqlExc) {
            System.out.println(">>> Exception in UserController class " + sqlExc);
            resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
        }
    }

    @PostMapping("/verify-user-credentials")
    public ResponseEntity<ResponseStatus> verifyUserCreds(@RequestBody User user) throws SQLException{
        UserRepository userRepository = new UserRepository();
        ResponseStatus resStatus;
        try{
            System.out.println(">>> inside UserController user.email is " + user.getEmail() + " user.password is " + user.getPassword());
            Boolean isValidCreds = UserRepository.verifyUserCreds(user);
            if(isValidCreds)            resStatus = new ResponseStatus("Found", "User exists");
            else                        resStatus = new ResponseStatus("NotFound", "User with provided credentials do not exist");
            return ResponseEntity.status(HttpStatus.OK).body(resStatus);
        } catch (SQLException sqlExc) {
            System.out.println(">>> Exception in UserController class " + sqlExc);
            resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
        }
    }
}