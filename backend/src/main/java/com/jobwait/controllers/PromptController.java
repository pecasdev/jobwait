package com.jobwait.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.jobwait.domain.Prompt;

@Controller
public class PromptController {
    @QueryMapping
    public Prompt promptById(@Argument String promptID) {
        return Prompt.getById(promptID);
    }
}
