package com.JobPortal.models;

public class SelectOptions {

	private Integer id;
	private String label;
	private String value;
	private Integer categoryId;

	public SelectOptions(String label, String value, Integer categoryId){
		this.label = label;
		this.value = value;
		this.categoryId = categoryId;
	}

	public SelectOptions(Integer id, String label, String value, Integer categoryId){
		this.id = id;
		this.label = label;
		this.value = value;
		this.categoryId = categoryId;
	}

	public Integer getId(){
		return id;
	}

	public String getLabel(){
		return label;
	}

	public String getValue(){
		return value;
	}

	public Integer getCategoryId(){
		return categoryId;
	}

	//There is no project use with this method.
	//This was used for displaying the selectOptions for debugging purposes.
	public void displaySelectOptions(){
		System.out.println(">>>> label= " + label + " value= " + value + " categoryId= " + categoryId);
	}
}