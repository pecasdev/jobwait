package com.jobwait.jackson;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class AnswerDeserializerFault extends FaultException {
    public AnswerDeserializerFault(String description) {
        super(HttpStatus.BAD_REQUEST, "INVALID_ANSWER_FORMAT", description);
    }
}
