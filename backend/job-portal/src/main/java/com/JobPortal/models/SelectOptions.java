package com.JobPortal.models;

public class SelectOptions {

	private Integer id;
	private String label;
	private Integer categoryId;

	public SelectOptions(String label, Integer categoryId){
		this.label = label;
		this.categoryId = categoryId;
	}

	public SelectOptions(Integer id, String label, Integer categoryId){
		this.id = id;
		this.label = label;
		this.categoryId = categoryId;
	}

	public Integer getId(){
		return id;
	}

	public String getLabel(){
		return label;
	}

	public Integer getCategoryId(){
		return categoryId;
	}

	//There is no project use with this method.
	//This was used for displaying the selectOptions for debugging purposes.
	public void displaySelectOptions(){
		System.out.println(">>>> label= " + label + " categoryId= " + categoryId);
	}
}