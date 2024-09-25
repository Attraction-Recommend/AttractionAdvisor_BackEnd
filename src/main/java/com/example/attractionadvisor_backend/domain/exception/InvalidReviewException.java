package com.example.attractionadvisor_backend.domain.exception;

public class InvalidReviewException extends RuntimeException {
    public InvalidReviewException(String message) {
        super(message);
    }
}