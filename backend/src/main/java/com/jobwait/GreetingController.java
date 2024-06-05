package com.jobwait;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobwait.db.DbConfiguration;
import com.jobwait.db.PostgreSQLExample;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("http://localhost:3000")
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	//#2 
	//Use created env configuration class to pull env vars from application.yml
	@Autowired
	private DbConfiguration dbconfig;

	//#1
	//PULL VALUES STRAIGHT FROM application.yml or Spring env
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	public String getDbEnvironmentVars() {
		// return dbconfig.getUsername();
		return dbUrl;
	}

	//Me being a little stupid LOL
	@PostMapping("/dbTest")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return getDbEnvironmentVars();
		// return attemptDbConnection();
	}
	
	public static String attemptDbConnection() {
		try {
			return String.valueOf(PostgreSQLExample.dbTest("jdbc:postgresql://localhost:5432/test", 
			"tester", 
			"password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.toString();
		}
	}

	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}