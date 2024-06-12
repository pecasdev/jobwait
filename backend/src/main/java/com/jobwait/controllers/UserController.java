package com.jobwait.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @QueryMapping
    public List<User> getAllUsers() {
        return User.getAllUsers();
    }

    @MutationMapping
    public User addUser(@Argument String name) {
        return User.addUser(name);
    }
}
