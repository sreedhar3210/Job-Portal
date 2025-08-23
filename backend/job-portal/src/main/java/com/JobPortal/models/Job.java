package com.JobPortal.models;

import java.time.LocalDate;

public class Job {
	
	private Integer id;
	private Integer roleId;						//foreign_key -> select_options(id)
	private String description;
	private String responsibilities;
	private String requirements;
	private Integer locationId;					//foreign_key -> select_options(id)
	private Integer employmentTypeId;			//foreign_key -> select_options(id)
	private Integer experienceLevelId;			//foreign_key -> select_options(id)
	private Integer salary;						//Number in LPA.
	private Integer companyId;					//foreign_key -> Company(id) => populated through company login.
	private LocalDate postedDate;
	private LocalDate lastDate;

	public Job(){}
	
	public Job(Integer id, Integer roleId, String description, String responsibilities,
			   String requirements, Integer locationId, Integer employmentTypeId, Integer experienceLevelId,
           	   Integer salary, Integer companyId, LocalDate postedDate, LocalDate lastDate){
  		this(roleId, description, responsibilities, requirements,
  		 	locationId, employmentTypeId, experienceLevelId,
       		salary, companyId, postedDate, lastDate);
		this.id = id;
	}

	public Job(Integer roleId, String description, String responsibilities, String requirements,
			   Integer locationId, Integer employmentTypeId, Integer experienceLevelId,
	           Integer salary, Integer companyId, LocalDate postedDate, LocalDate lastDate){
		this.roleId = roleId;
		this.description = description;
		this.responsibilities = responsibilities;
		this.requirements = requirements;
		this.locationId = locationId;
		this.employmentTypeId = employmentTypeId;
		this.experienceLevelId = experienceLevelId;
		this.salary = salary;
		this.companyId = companyId;
		this.postedDate = postedDate;
		this.lastDate = lastDate;
	}

	public Integer getId(){
       	return id;
    }

    public Integer getRoleId(){
   		return roleId;
    }

    public String getDescription(){
	    return description;
    }

    public String getResponsibilities(){
  	    return responsibilities;
    }

    public String getRequirements(){
    	return requirements;
    }

    public Integer getLocationId(){
    	return locationId;
    }

    public Integer getEmploymentTypeId(){
        return employmentTypeId;
    }

    public Integer getExperienceLevelId(){
    	return experienceLevelId;
    }

    public Integer getSalary(){
      	return salary;
	}

    public Integer getCompanyId(){
       	return companyId;
    }
    
	public LocalDate getPostedDate(){
 		return postedDate;
    }
    
    public LocalDate getLastDate(){
   		return lastDate;
  	}
}