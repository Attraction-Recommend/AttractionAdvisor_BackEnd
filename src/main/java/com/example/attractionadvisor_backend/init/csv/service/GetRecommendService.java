package com.example.attractionadvisor_backend.init.csv.service;

import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendRequest;
import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class GetRecommendService {
    private final WebClient webClient;

    @Value("${python.api.url}")
    private String pythonApiUrl;

    public Mono<RecommendResponse> getRecommendations(String travelId) {
        RecommendRequest request = new RecommendRequest(travelId);

        return webClient.post()
                .uri(pythonApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RecommendResponse.class)
                .onErrorMap(this::handleApiError);
    }

    private Throwable handleApiError(Throwable error) {
        return new IllegalArgumentException("Error fetching recommendations", error);
    }
}