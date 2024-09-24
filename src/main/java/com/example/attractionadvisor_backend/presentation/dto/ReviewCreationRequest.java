package com.example.attractionadvisor_backend.presentation.dto;

import lombok.Data;

@Data
public class ReviewCreationRequest {
    private Long userId;
    private String destinationId;
    private String content;
    private int rating;
}