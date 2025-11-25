package com.rebook.passport;

public class PassportException extends RuntimeException {
    public PassportException(String message) {
        super(message);
    }

    public PassportException(String message, Throwable cause) {
        super(message, cause);
    }
}
