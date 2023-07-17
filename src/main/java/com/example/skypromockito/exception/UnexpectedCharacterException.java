package com.example.skypromockito.exception;

public class UnexpectedCharacterException extends RuntimeException {
    public UnexpectedCharacterException() {
        super();
    }
    public UnexpectedCharacterException(String message) {
        super(message);
    }
    public UnexpectedCharacterException(String message, Throwable t) {
        super(message, t);
    }

    public UnexpectedCharacterException(Throwable t) {
        super(t);
    }

}
