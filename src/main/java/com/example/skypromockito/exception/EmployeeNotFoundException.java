package com.example.skypromockito.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super();
    }
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    public EmployeeNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeNotFoundException(Throwable t) {
        super(t);
    }

}
