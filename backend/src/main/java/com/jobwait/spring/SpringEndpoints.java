package com.jobwait.spring;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobwait.fault.FaultException;
import com.jobwait.spring.utils.Utils;

@CrossOrigin(origins={"http://127.0.0.1:3000", "http://localhost:3000", "https://jobwait.pecas.dev"})
@RestController
public class SpringEndpoints {
	private static ResponseEntity<ObjectNode> processAndHandleException(Supplier<ObjectNode> func) {
		ObjectNode responseNode = Utils.mapper.createObjectNode();

		try {
			ObjectNode data = func.get();
			if (data == null) {
				return new ResponseEntity<ObjectNode>(HttpStatus.OK);
			} else {
				responseNode.set("data", data);
				return new ResponseEntity<ObjectNode>(responseNode, HttpStatus.OK);
			}
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
	public static ResponseEntity<ObjectNode> createUserFromAuthToken(@RequestParam("at") String authToken) {
		return processAndHandleException(RequestNavigation.createUserFromAuthToken(authToken));
	}

	@GetMapping("/answers")
	public static ResponseEntity<ObjectNode> getUserAnswers(@RequestParam("at") String authToken) {
		return processAndHandleException(RequestNavigation.getUserAnswers(authToken));
	}

	@PostMapping("/answers/submit")
	public static ResponseEntity<ObjectNode> submitUserAnswers(@RequestParam("at") String authToken,
			@RequestBody String payload) {
		return processAndHandleException(RequestNavigation.submitUserAnswers(authToken, payload));
	}

	@GetMapping("/stat")
	public static ResponseEntity<ObjectNode> getStat(@RequestParam("id") String statId) {
		return processAndHandleException(RequestNavigation.getStat(statId));
	}
}
