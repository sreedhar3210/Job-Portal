package com.JobPortal.controllers;

import com.JobPortal.models.User;
import com.JobPortal.repository.UserRepository;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000"}) // Allowing localhost:3000 apis
public class UserController {

    @PostMapping("/insert-user")
    public void insertUser(@RequestBody User user) throws SQLException{
    	UserRepository userRepository = new UserRepository();
        try {
            System.out.println(">>> inside UserController user.firstname is "+ user.getFirstname() + " user.lastname is " + user.getLastname());
        	UserRepository.insertUser(user);

        } catch (SQLException sqlExc) {
        	System.out.println(">>> Exception in UserController class " + sqlExc);
            throw sqlExc;

        }
    }
}