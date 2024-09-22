package com.example.attractionadvisor_backend.presentation;

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