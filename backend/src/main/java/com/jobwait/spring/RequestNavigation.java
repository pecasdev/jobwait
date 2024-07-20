package com.jobwait.spring;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobwait.control.RequestController;
import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.security.AuthToken;

@CrossOrigin("http://localhost:3000")
@RestController
public class RequestNavigation {
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

	@GetMapping(path = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
	public static Map getUserAnswers(@RequestParam("at") String authToken) {
		AuthToken token = AuthToken.fromClientId(authToken);
		Map answers = requestController.getUserAnswers(token);
		return answers;
	}

	@PostMapping(path = "/answer/submit", produces = MediaType.APPLICATION_JSON_VALUE)
	public static Map submitUserAnswers(@RequestParam("at") String authToken, @RequestBody Answers answers) {
		AuthToken token = AuthToken.fromClientId(authToken);
		Map returnMap = requestController.submitUserAnswers(token, answers);
		return returnMap;
	}
}
