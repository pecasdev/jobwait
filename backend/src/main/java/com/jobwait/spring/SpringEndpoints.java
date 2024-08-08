package com.jobwait.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobwait.control.RequestController;
import com.jobwait.domain.Answer;
import com.jobwait.fault.FaultException;
import com.jobwait.security.AuthToken;
import com.jobwait.spring.utils.Utils;

// todo - remove all try/catch blocks from jobwait, there should be a single try/catch at the end or whenever we want to convert a runtimeexception/sqlexception into a more generic Fault exception
@CrossOrigin("http://127.0.0.1:3000")
@RestController
public class SpringEndpoints {
	private static RequestController requestController = new RequestController();

	private static ResponseEntity<ObjectNode> processAndHandleException(Supplier<ObjectNode> func) {
		ObjectNode responseNode = Utils.mapper.createObjectNode();
		responseNode.set("data", null);
		responseNode.set("error", null);

		try {
			responseNode.set("data", func.get());
			return new ResponseEntity<ObjectNode>(responseNode, HttpStatus.OK);
		} catch (FaultException e) {
			ObjectNode errorNode = Utils.mapper.createObjectNode();
			errorNode.put("shortCode", e.shortCode);
			errorNode.put("description", e.description);

			responseNode.set("error", errorNode);
			return new ResponseEntity<ObjectNode>(responseNode, e.statusCode);
		}
	}

	@GetMapping("/user")
	public static ResponseEntity<ObjectNode> getUserFromAuthToken(@RequestParam("at") String authToken) {
		return processAndHandleException(RequestNavigation.getUserFromAuthToken(authToken));
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
			JsonNode root = Utils.mapper.readTree(payload).path("answers");
			Answer[] answersArray = Utils.mapper.treeToValue(root, Answer[].class);
			List<Answer> answersList = Arrays.asList(answersArray);

			AuthToken token = AuthToken.fromClientId(authToken);
			requestController.submitUserAnswers(token, answersList);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (JsonProcessingException | RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
