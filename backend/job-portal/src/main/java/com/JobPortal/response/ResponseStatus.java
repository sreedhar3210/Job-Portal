package com.JobPortal.response;

public class ResponseStatus {
	private String status;
	private String message;

	public ResponseStatus(String status, String message){
		this.status = status;
		this.message = message;
	}

	public String getStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}
}