package com.jobwait.spring;

import java.util.function.Supplier;

import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobwait.control.RequestController;
import com.jobwait.domain.User;
import com.jobwait.security.AuthToken;
import com.jobwait.spring.utils.Utils;


public class RequestNavigation {
	private static RequestController requestController = new RequestController();

	public static Supplier<ObjectNode> getUserFromAuthToken(@RequestParam("at") String authToken) {
		return () -> {
			AuthToken token = AuthToken.fromClientId(authToken);
			User user = requestController.getUserFromAuthToken(token);
	
			ObjectNode objectNode = Utils.mapper.createObjectNode();
			objectNode.put("userId", user.id().toString());
			
			return objectNode;
		};
	}
}
