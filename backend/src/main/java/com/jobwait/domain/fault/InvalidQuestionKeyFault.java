package com.jobwait.domain.fault;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class InvalidQuestionKeyFault extends FaultException {
    public InvalidQuestionKeyFault(String key) {
        super(HttpStatus.BAD_REQUEST, "INVALID_QUESTION_KEY",
                "questionKey '%s' does not exist in known questions".formatted(key));
    }
}
