package com.jobwait.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.jobwait.domain.User;

@Controller
public class UserController {
    @QueryMapping
    public User userById(@Argument String userID) {
        User foundUser = User.getById(userID);
        return foundUser;
    }
}
