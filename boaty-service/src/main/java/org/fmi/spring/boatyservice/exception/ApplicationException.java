package org.fmi.spring.boatyservice.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private HttpStatus statusCode;

    public ApplicationException(String message, Throwable reason, HttpStatus statusCode) {
        super(message, reason);
        this.statusCode = statusCode;
    }

    public ApplicationException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
