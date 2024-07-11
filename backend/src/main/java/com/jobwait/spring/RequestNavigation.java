package com.jobwait.spring;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobwait.control.RequestController;
import com.jobwait.db.PostgreSQLExample;
import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.security.AuthToken;

@CrossOrigin("http://localhost:3000")
@RestController
public class RequestNavigation {
	// #2
	// Use created env configuration class to pull env vars from application.yml
	// @Autowired
	// private DbConfiguration dbconfig;

	// #1
	// PULL VALUES STRAIGHT FROM application.yml or Spring env
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

	// Me being a little stupid LOL
	@PostMapping("/dbTest")
	public String postMethodName(@RequestBody String entity) {
		// TODO: process POST request

		// return getDbEnvironmentVars();
		return attemptDbConnection();
	}

	public static String attemptDbConnection() {
		try {
			return String.valueOf(
					PostgreSQLExample.dbTest("jdbc:postgresql://localhost:5432/mydatabase",
							"postgres",
							"password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.toString();
		}
	}

	private static RequestController requestController = new RequestController();

	@GetMapping("/user")
	public static String getUserFromAuthToken(@RequestParam("at") String authToken) {
		AuthToken token = AuthToken.fromClientId(authToken);
		User user = requestController.getUserFromAuthToken(token);
		return user.id().toString();
	}

	@PostMapping("/user/create")
	public static String createUserFromAuthToken(@RequestParam("at") String authToken) {
		AuthToken token = AuthToken.fromClientId(authToken);
		User user = requestController.createUserFromAuthToken(token);
		return user.id().toString();
	}

	@PostMapping("/answer")
	public static String getUserAnswers(@RequestParam("at") String authToken) {
		AuthToken token = AuthToken.fromClientId(authToken);
		Answers answers = requestController.getUserAnswers(token);
		return answers.toString();
	}

	@PostMapping("/answer/submit")
	public static String submitUserAnswers(@RequestParam("at") String authToken, @RequestBody Answers answers) {
		AuthToken token = AuthToken.fromClientId(authToken);
		User user = requestController.submitUserAnswers(token, answers);
		return user.id().toString();
	}
}
