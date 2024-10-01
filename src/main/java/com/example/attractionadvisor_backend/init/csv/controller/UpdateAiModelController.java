package com.example.attractionadvisor_backend.init.csv.controller;

import com.example.attractionadvisor_backend.init.csv.service.UpdateRecommendModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UpdateAiModelController {

    private final UpdateRecommendModelService updateRecommendModelService;

    @PostMapping("/updateModel")
    public Mono<ResponseEntity<String>> updateModel() {
        return updateRecommendModelService.updateModel()
                .map(ResponseEntity::ok)
                .onErrorResume(this::handleError);
    }

    private Mono<ResponseEntity<String>> handleError(Throwable error) {
        log.error("Error updating model", error);
//        if (error instanceof RecommendationException) {
//            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating model: " + error.getMessage()));
//        }
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred while updating the model"));
    }
}
