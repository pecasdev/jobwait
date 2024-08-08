package com.jobwait.fault;

import org.springframework.http.HttpStatusCode;

public class FaultException extends RuntimeException {
    public HttpStatusCode statusCode;
    public String shortCode;
    public String description;

    public FaultException(HttpStatusCode statusCode, String shortCode, String description) {
        this.statusCode = statusCode;
        this.shortCode = shortCode;
        this.description = description;
    }

    public String toString() {
        return "[%d] (%s) %s".formatted(this.statusCode.value(), this.shortCode, this.description);
    }
}
