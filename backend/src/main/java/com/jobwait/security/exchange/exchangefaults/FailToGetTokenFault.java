package com.jobwait.security.exchange.exchangefaults;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class FailToGetTokenFault extends FaultException {
    public FailToGetTokenFault(String message) {
        super(HttpStatus.UNAUTHORIZED, "FAILED_TO_GET_ACCESS_TOKEN",
                "Could not retrieve access token. %s".formatted(message));
    }
}
