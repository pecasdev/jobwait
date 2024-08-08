package com.jobwait.domain;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class InvalidQuestionKey extends FaultException {
    public InvalidQuestionKey(String key) {
        super(HttpStatus.BAD_REQUEST, "INVALID_QUESTION_KEY",
                "questionKey '%s' does not exist in known questions".formatted(key));
    }
}
