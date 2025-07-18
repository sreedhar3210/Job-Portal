package com.JobPortal.models;

public class Company {

	private Integer id;
	private String companyName;
	private Integer industryType;
	private String companyURL;
	private Integer companySize;
	private String companyDescription;
	private String companyEmail;
	private String companyPassword;

	//This no-args constructor is needed for deserializing to this object.
	public Company(){}

	public Company(Integer id, String companyName, Integer industryType, String companyURL,
					Integer companySize, String companyDescription, String companyEmail, String companyPassword){
		this(companyName, industryType, companyURL, companySize, companyDescription, companyEmail, companyPassword);
		this.id = id;	
	}

	public Company(String companyName, Integer industryType, String companyURL,
					Integer companySize, String companyDescription, String companyEmail, String companyPassword){
		this.companyName = companyName;
		this.industryType = industryType;
		this.companyURL = companyURL;
		this.companySize = companySize;
		this.companyDescription = companyDescription;
		this.companyEmail = companyEmail;
		this.companyPassword = companyPassword;
	}
	
	public Integer getId(){
		return id;
	}

	public String getCompanyName(){
		return companyName;
	}

	public Integer getIndustryType(){
		return industryType;
	}

	public String getCompanyURL(){
		return companyURL;
	}

	public Integer getCompanySize(){
		return companySize;
	}

	public String getCompanyDescription(){
		return companyDescription;
	}

	public String getCompanyEmail(){
		return companyEmail;
	}

	public String getCompanyPassword(){
		return companyPassword;
	}

	public void displayCompanyValues(){
		System.out.println(">>>> displaying Company Values");
		System.out.println(">>>> companyEmail is " + companyEmail);
		System.out.println(">>>> company Password is " + companyPassword);
	}
}