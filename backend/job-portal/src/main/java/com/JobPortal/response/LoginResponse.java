package com.JobPortal.response;

import com.JobPortal.models.User;
import com.JobPortal.models.Company;

public class LoginResponse {

    private Boolean isUserSignin;
    private Boolean isCompanySignin;
    private String status;
    private String message;
    private User user;
    private Company company;

	public LoginResponse(
        Boolean isUserSignin,
        Boolean isCompanySignin,
        String status,
        String message,
        User user,
        Company company
    ){
		this.isUserSignin = isUserSignin;
		this.isCompanySignin = isCompanySignin;
        this.status = status;
        this.message = message;
        this.user = user;
		this.company = company;
	}

	public Boolean getIsUserSignin(){
		return isUserSignin;
	}

	public Boolean getIsCompanySignin(){
		return isCompanySignin;
	}

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

	public User getUser(){
		return user;
	}

    public Company getCompany() {
        return company;
    }
}