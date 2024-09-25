package com.example.attractionadvisor_backend.domain.exception;

public class VisitNotFoundException extends RuntimeException {
    public VisitNotFoundException(String message) {
        super(message);
    }
}