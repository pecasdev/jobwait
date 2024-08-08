package com.jobwait.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jobwait.control.RequestController;
import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.security.AuthToken;
import com.jobwait.spring.utils.Utils;

@CrossOrigin("http://127.0.0.1:3000")
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
	public static ResponseEntity<String> createUserFromAuthToken(@RequestParam("at") String authToken) {
		try {
			AuthToken token = AuthToken.fromClientId(authToken);
			requestController.createUserFromAuthToken(token);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/answers", produces = MediaType.APPLICATION_JSON_VALUE)
	public static String getUserAnswers(@RequestParam("at") String authToken) {
		try {
			AuthToken token = AuthToken.fromClientId(authToken);
			List<Answer> answers = requestController.getUserAnswers(token);
			return Utils.mapper.writeValueAsString(answers);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}

	@PostMapping(path = "/answers/submit")
	public static ResponseEntity<String> submitUserAnswers(@RequestParam("at") String authToken,
			@RequestBody String payload) {
		try {
			Answers answers = Utils.mapper.readValue(payload, Answers.class);

			AuthToken token = AuthToken.fromClientId(authToken);
			requestController.submitUserAnswers(token, answers);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (JsonProcessingException | RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
