package com.example.attractionadvisor_backend.application.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private Long userId;
    private String destinationId;
    private String content;
    private int rating;
    private LocalDateTime createdAt;

    // Constructor, getters, and setters
}