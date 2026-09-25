package com.JobPortal.models;

public class Company {

	private Integer id;
	private String companyName;
	private Integer industryTypeId;
	private String industryType;
	private String companyURL;
	private Integer companySizeId;
	private String companySize;
	private String companyDescription;
	private String companyEmail;
	private String companyPassword;

	//This no-args constructor is needed for deserializing to this object.
	public Company(){}

	public Company(Integer id, String companyName, Integer industryTypeId, String companyURL,
					Integer companySizeId, String companyDescription, String companyEmail, String companyPassword){
		this(companyName, industryTypeId, companyURL, companySizeId, companyDescription, companyEmail, companyPassword);
		this.id = id;	
	}

	public Company(String companyName, Integer industryTypeId, String companyURL,
					Integer companySizeId, String companyDescription, String companyEmail, String companyPassword){
		this.companyName = companyName;
		this.industryTypeId = industryTypeId;
		this.companyURL = companyURL;
		this.companySizeId = companySizeId;
		this.companyDescription = companyDescription;
		this.companyEmail = companyEmail;
		this.companyPassword = companyPassword;
	}

	public void setId(Integer companyId) {
		this.id = companyId;
	}

	public Integer getId(){
		return id;
	}

	public String getCompanyName(){
		return companyName;
	}

	public Integer getIndustryTypeId(){
		return industryTypeId;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getIndustryType() {
		return industryType;
	}

	public String getCompanyURL(){
		return companyURL;
	}

	public Integer getCompanySizeId(){
		return companySizeId;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanySize() {
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