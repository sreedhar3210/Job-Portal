package com.JobPortal.models;

public class OptionCategory {

	private Integer id;
	private String name;

	public OptionCategory(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public OptionCategory(String name){
		this.name = name;
	}

	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}