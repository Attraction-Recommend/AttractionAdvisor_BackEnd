package com.example.attractionadvisor_backend.presentation.request;

import lombok.Data;

@Data
public class VisitRecordRequest {
    private String userId;
    private String destinationId;
    private String visitStart;
    private String visitEnd;
}