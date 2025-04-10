package org.example.tails.exception;

public class DeptOperationException extends RuntimeException {
    private String message;

    public DeptOperationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DeptOperationException{" +
                "message='" + message + '\'' +
                '}';
    }
}