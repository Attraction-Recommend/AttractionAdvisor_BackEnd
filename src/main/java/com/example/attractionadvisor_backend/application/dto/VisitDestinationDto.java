package com.example.attractionadvisor_backend.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class VisitDestinationDto {
    private Long id;
    private String userId;
    private String destinationId;
    private LocalDate visitStart;
    private LocalDate visitEnd;

    // Constructor, getters, and setters
}