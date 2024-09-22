package com.example.attractionadvisor_backend.application;


import com.example.attractionadvisor_backend.exception.RecommendationException;
import com.example.attractionadvisor_backend.presentation.RecommendRequest;
import com.example.attractionadvisor_backend.presentation.RecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class AttractionRecommendService {
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
        return new RecommendationException("Error fetching recommendations", error);
    }
}