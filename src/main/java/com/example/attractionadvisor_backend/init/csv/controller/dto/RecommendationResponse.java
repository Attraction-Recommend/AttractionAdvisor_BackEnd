package com.example.attractionadvisor_backend.init.csv.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecommendationResponse {
    private String travelId;
    private String poiId;
    private String poiNm;

    private String x_coord;
    private String y_coord;
}

