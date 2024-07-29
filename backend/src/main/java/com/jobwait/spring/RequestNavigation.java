package com.jobwait.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
	public static String getUserAnswers(@RequestParam("at") String authToken) {
		try {
			AuthToken token = AuthToken.fromClientId(authToken);
			Answers answers = requestController.getUserAnswers(token);
			return new ObjectMapper().writeValueAsString(answers);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}

	@PostMapping(path = "/answer/submit", produces = MediaType.APPLICATION_JSON_VALUE)
	public static String submitUserAnswers(@RequestParam("at") String authToken, @RequestBody String payload) {
		try {
			ObjectMapper mapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
					.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
							true)
					.build();

			Answers answers = mapper.readValue(payload, Answers.class);

			AuthToken token = AuthToken.fromClientId(authToken);
			Answers returnAnswers = requestController.submitUserAnswers(token, answers);
			return mapper.writeValueAsString(returnAnswers);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}

	}
}
