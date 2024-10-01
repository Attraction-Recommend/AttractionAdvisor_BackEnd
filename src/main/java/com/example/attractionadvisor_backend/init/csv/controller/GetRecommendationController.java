package com.example.attractionadvisor_backend.init.csv.controller;

import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendRequest;
import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendResponse;
import com.example.attractionadvisor_backend.init.csv.service.GetRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GetRecommendationController {
    private final GetRecommendService recommendationService;

    @PostMapping("/recommend")
    public Mono<ResponseEntity<RecommendResponse>> getRecommendations(@RequestBody RecommendRequest request) {
        return recommendationService.getRecommendations(request.getTravel_id())
                .map(ResponseEntity::ok)
                .onErrorResume(this::handleError);
    }

    private Mono<ResponseEntity<RecommendResponse>> handleError(Throwable error) {
        return Mono.just(ResponseEntity.internalServerError()
                .body(new RecommendResponse())); // 에러 시 빈 응답 반환
    }
}