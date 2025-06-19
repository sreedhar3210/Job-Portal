package com.JobPortal.job_portal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello{

	@RequestMapping("/")
	public String greet(){
		return "Hello welcome to the java spring boot server";
	}

	@RequestMapping("/check")
	public String checking(){
		return "hello we are checking thhis new api";
	}
}