package com.JobPortal.job_portal;

import com.JobPortal.models.User;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
	exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
	}
)

//@EnableAutoConfiguration
//we need to specify the entities folder in basePackages so that they will become visible to this main file.
@ComponentScan(basePackages = {"com.JobPortal"})
public class JobPortalApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{

		SpringApplication.run(JobPortalApplication.class, args);
		System.out.println(">>>> Job Application is running successfully");

	}

}
