package com.example.rate_limiting_demo.exception;

public class ResourceExistsException extends RuntimeException{

    public ResourceExistsException(String message) {
        super(message);
    }
}
