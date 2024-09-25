package com.example.attractionadvisor_backend.domain.exception;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(String message) {
        super(message);
    }
}
