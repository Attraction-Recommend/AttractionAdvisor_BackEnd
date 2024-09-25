package com.example.attractionadvisor_backend.application.dto;

import lombok.Data;

@Data
public class DestinationDto {
    private String destinationId;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;

    // Constructor, getters, and setters
}