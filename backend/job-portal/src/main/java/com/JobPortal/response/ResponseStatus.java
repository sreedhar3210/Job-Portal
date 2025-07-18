package com.JobPortal.response;

public class ResponseStatus<T> {
	private String status;
	private String message;
	private T data;

	public ResponseStatus(String status, String message){
		this.status = status;
		this.message = message;
	}

	public ResponseStatus(String status, String message, T data){
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}

	public T getData(){
		return data;
	}
}