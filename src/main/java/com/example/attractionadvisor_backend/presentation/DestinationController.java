package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.DestinationUseCase;
import com.example.attractionadvisor_backend.domain.entity.Destination;
import com.example.attractionadvisor_backend.presentation.dto.DestinationCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationUseCase destinationUseCase;

    @PostMapping
    public ResponseEntity<Destination> addDestination(@RequestBody DestinationCreationRequest request) {
        Destination destination = destinationUseCase.addDestination(
                request.getDestinationId(),
                request.getName(),
                request.getDescription(),
                request.getLatitude(),
                request.getLongitude()
        );
        return ResponseEntity.ok(destination);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Destination>> searchDestinations(
            @RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(destinationUseCase.searchDestinations(keyword, pageable));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<Page<Destination>> getTopRatedDestinations(Pageable pageable) {
        return ResponseEntity.ok(destinationUseCase.getTopRatedDestinations(pageable));
    }
}