package com.jobwait.domain;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class UndefinedAnswerTypeFault extends FaultException {
    public UndefinedAnswerTypeFault(AnswerType answerType, String location) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "UNDEFINED_ANSWER_TYPE",
                "No defined behavior for AnswerType (%s) in location: %s".formatted(answerType, location));
    }
}
