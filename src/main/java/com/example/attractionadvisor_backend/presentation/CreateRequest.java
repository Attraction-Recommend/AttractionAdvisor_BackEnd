package com.example.attractionadvisor_backend.presentation;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateRequest {
    @Column(name = "POI_ID")
    private String poiId;
    @Column(name = "POI_NM")
    private String poiNm;
}
