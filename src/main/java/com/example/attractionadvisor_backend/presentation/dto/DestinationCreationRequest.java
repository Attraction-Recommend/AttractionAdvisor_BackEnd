package com.example.attractionadvisor_backend.presentation.dto;

import lombok.Data;

@Data
public class DestinationCreationRequest {
    private String destinationId;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
}