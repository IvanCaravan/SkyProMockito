package com.example.skypromockito.exception;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException() {
        super();
    }
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
    public EmployeeAlreadyAddedException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeAlreadyAddedException(Throwable t) {
        super(t);
    }
}
