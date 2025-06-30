package com.JobPortal.models;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Column;

//@Entity
public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User(String firstname, String lastname, String email, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
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
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "Id")
    // private Long id;

    // @Column(name = "Firstname")
    // private String firstname;

    // @Column(name = "Lastname")
    // private String lastname;

    // @Column(name = "Date of Birth")
    // private Date dateOfBirth;

    // @Column(name = "Email")
    // private String email;

    // @Column(name = "Password")
    // private String password;

}