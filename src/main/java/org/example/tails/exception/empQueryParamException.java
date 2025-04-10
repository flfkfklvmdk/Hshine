package org.example.tails.exception;

public class empQueryParamException extends Exception {
    private String message;

    public empQueryParamException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "empQueryParamException{" +
                "message='" + message + '\'' +
                '}';
    }
}
