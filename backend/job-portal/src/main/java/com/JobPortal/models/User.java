package com.JobPortal.models;

public class User {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    //This no-args constructor is needed for deserializing to this object.
    public User(){}

    public User(Integer id, String firstname, String lastname, String email, String password){
        this(firstname, lastname, email, password);  // calling the 4-arg constructor
        this.id = id;
    }

    public User(String firstname, String lastname, String email, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Integer getId(){
        return id;
    }
    
    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}