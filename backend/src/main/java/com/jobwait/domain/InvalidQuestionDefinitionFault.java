package com.jobwait.domain;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class InvalidQuestionDefinitionFault extends FaultException {
    public InvalidQuestionDefinitionFault(String questionKey) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "INVALID_QUESTION_DEFINITION",
                "Question(%s) of type ENUM has no valid choices".formatted(questionKey));
    }
}
