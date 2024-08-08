package com.jobwait.spring;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobwait.control.RequestController;
import com.jobwait.domain.Answer;
import com.jobwait.domain.User;
import com.jobwait.jackson.AnswerDeserializerFault;
import com.jobwait.security.AuthToken;
import com.jobwait.spring.utils.Utils;

public class RequestNavigation {
	private static RequestController requestController = new RequestController();

	public static Supplier<ObjectNode> getUserFromAuthToken(String authToken) {
		return () -> {
			AuthToken token = AuthToken.fromClientId(authToken);
			User user = requestController.getUserFromAuthToken(token);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			dataNode.put("userId", user.id().toString());

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> createUserFromAuthToken(String authToken) {
		return () -> {
			AuthToken token = AuthToken.fromClientId(authToken);
			User user = requestController.createUserFromAuthToken(token);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			dataNode.put("userId", user.id().toString());

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> getUserAnswers(String authToken) {
		return () -> {
			AuthToken token = AuthToken.fromClientId(authToken);
			List<Answer> answers = requestController.getUserAnswers(token);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			ObjectNode answersNode = Utils.mapper.valueToTree(answers);
			dataNode.set("answers", answersNode);

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> submitUserAnswers(String authToken, String payload) {
		return () -> {
			try {
				JsonNode root = Utils.mapper.readTree(payload).path("answers");
				Answer[] answersArray = Utils.mapper.treeToValue(root, Answer[].class);
				List<Answer> answersList = Arrays.asList(answersArray);

				AuthToken token = AuthToken.fromClientId(authToken);
				requestController.submitUserAnswers(token, answersList);

				return null;
			} catch (JsonProcessingException e) {
				e.clearLocation();
				throw new AnswerDeserializerFault(e.getMessage());
			}
		};
	}
}
