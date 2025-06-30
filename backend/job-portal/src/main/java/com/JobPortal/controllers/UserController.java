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

    @PostMapping("/insert-user")
    public ResponseEntity<ResponseStatus> insertUser(@RequestBody User user) throws SQLException{
    	UserRepository userRepository = new UserRepository();
        ResponseStatus resStatus;
        try {
            System.out.println(">>> inside UserController user.firstname is "+ user.getFirstname() + " user.lastname is " + user.getLastname()
                                + " user.email is " + user.getEmail() + " user.password is " + user.getPassword());
        	Integer rowsEffected = UserRepository.insertUser(user);
            if(rowsEffected == 0)       resStatus = new ResponseStatus("UserWithSameEmailFound", "We have found user with same email");
            else                        resStatus = new ResponseStatus("UserInserted", "Given user is inserted");

            return ResponseEntity.status(HttpStatus.CREATED).body(resStatus);
        } catch (SQLException sqlExc) {
            System.out.println(">>> Exception in UserController class " + sqlExc);
            resStatus = new ResponseStatus("Error", "User Insertion is failed with Error: " + sqlExc.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resStatus);
        }
    }

    // @PostMapping("/verify-user-credentials")
    // public void verifyUserCreds(@RequestBody User user) throws SQLException{
    //     UserRepository userRepository = new UserRepository();

    //     try{
    //         System.out.println(">>> inside UserController user.email is " + user.getEmail() + " user.password is " + user.getPassword());

    //         UserRepository.
    //     }
    // }
}