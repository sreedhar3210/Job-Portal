package com.JobPortal.job_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JobPortalApplication.class, args);
		System.out.println(">>>> Job Application is running successfully");
	}

}
