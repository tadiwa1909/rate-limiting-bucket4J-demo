package com.example.rate_limiting_demo.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
