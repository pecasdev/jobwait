package com.jobwait.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobwait.control.RequestController;
import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.domain.User;
import com.jobwait.jackson.AnswerDeserializerFault;
import com.jobwait.spring.utils.Utils;

public class RequestNavigation {
	private static RequestController requestController = new RequestController();

	public static Supplier<ObjectNode> getUserFromAuthCode(String authCode) {
		return () -> {
			User user = requestController.getUserFromAuthCode(authCode);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			dataNode.put("userId", user.id().toString());

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> createUserFromAuthCode(String authCode) {
		return () -> {
			User user = requestController.createUserFromAuthCode(authCode);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			dataNode.put("userId", user.id().toString());

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> getUserAnswers(String authCode) {
		return () -> {
			List<Answer> answers = requestController.getUserAnswers(authCode);

			ObjectNode dataNode = Utils.mapper.createObjectNode();
			ObjectNode answersNode = Utils.mapper.valueToTree(answers);
			dataNode.set("answers", answersNode);

			return dataNode;
		};
	}

	public static Supplier<ObjectNode> submitUserAnswers(String authCode, String payload) {
		return () -> {
			try {
				JsonNode root = Utils.mapper.readTree(payload).path("answers");
				List<Answer> answersList = Arrays.asList(Utils.mapper.treeToValue(root, Answer[].class));

				requestController.submitUserAnswers(authCode, answersList);

				return null;
			} catch (JsonProcessingException e) {
				e.clearLocation();
				throw new AnswerDeserializerFault(e.getMessage());
			}
		};
	}

	public static Supplier<ObjectNode> getStat(String statId) {
		return () -> {
			Stat stat = requestController.getStat(statId);
			Map<String, Object> rows = stat.getRows();

			ObjectNode statNode = Utils.mapper.createObjectNode();
			statNode.put("type", stat.type);
			statNode.put("title", stat.title);
			statNode.put("xAxisLabel", stat.xAxisLabel);
			statNode.put("yAxisLabel", stat.yAxisLabel);
			statNode.set("rows", Utils.mapper.valueToTree(rows));

			return statNode;
		};
	}
}
