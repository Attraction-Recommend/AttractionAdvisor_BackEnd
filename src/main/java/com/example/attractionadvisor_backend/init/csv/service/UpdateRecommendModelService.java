package com.example.attractionadvisor_backend.init.csv.service;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendRequest;
import com.example.attractionadvisor_backend.init.csv.controller.dto.RecommendResponse;

import com.example.attractionadvisor_backend.init.csv.jpa.VisitDestinationCsvRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateRecommendModelService {


        private final WebClient webClient;
        private final VisitDestinationCsvRepository visitDestinationCsvRepository;

        @Value("${python.api.url}")
        private String pythonApiUrl;

        @Value("${python.api.update-model-path}")
        private String updateModelPath;

        public Mono<RecommendResponse> getRecommendations(RecommendRequest request) {
            return webClient.post()
                    .uri(pythonApiUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(request), RecommendRequest.class)
                    .retrieve()
                    .bodyToMono(RecommendResponse.class)
                    .onErrorMap(this::handleApiError);
        }

        @Transactional(readOnly = true)
        public Mono<String> updateModel() {
            List<VisitDestination> attractions = visitDestinationCsvRepository.findAll();
            Map<String, Object> requestBody = Map.of("attractions", mapAttractionsToDto(attractions));

            return webClient.post()
                    .uri(updateModelPath)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(requestBody), Map.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSubscribe(subscription -> log.info("Sending request to update model with {} attractions", attractions.size()))
                    .doOnSuccess(response -> log.info("Model update successful"))
                    .onErrorMap(this::handleApiError);
        }

        private List<Map<String, Object>> mapAttractionsToDto(List<VisitDestination> attractions) {
            return attractions.stream()
                    .map(visitDestination -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("TRAVEL_ID", visitDestination.getUserId());
                        map.put("POI_ID", visitDestination.getDestinationId());
                        map.put("POI_NM", visitDestination.getDestinationName());
                        return map;
                    })
                    .collect(Collectors.toList());
        }

        private Throwable handleApiError(Throwable error) {
            log.error("API error occurred", error);
            return new IllegalArgumentException("Error in attraction service", error);
        }

}