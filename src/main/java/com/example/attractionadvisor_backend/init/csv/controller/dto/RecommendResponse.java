package com.example.attractionadvisor_backend.init.csv.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecommendResponse {
    private List<RecommendItem> recommendations;


}