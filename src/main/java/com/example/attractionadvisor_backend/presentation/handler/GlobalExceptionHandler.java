package com.example.attractionadvisor_backend.presentation.handler;

import com.example.attractionadvisor_backend.domain.exception.DestinationNotFoundException;
import com.example.attractionadvisor_backend.domain.exception.DuplicateUserException;
import com.example.attractionadvisor_backend.domain.exception.InvalidReviewException;
import com.example.attractionadvisor_backend.domain.exception.ReviewNotFoundException;
import com.example.attractionadvisor_backend.domain.exception.UserNotFoundException;
import com.example.attractionadvisor_backend.domain.exception.VisitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class, DestinationNotFoundException.class, ReviewNotFoundException.class, VisitNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidReviewException.class)
    public ResponseEntity<String> handleInvalidReviewException(InvalidReviewException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}