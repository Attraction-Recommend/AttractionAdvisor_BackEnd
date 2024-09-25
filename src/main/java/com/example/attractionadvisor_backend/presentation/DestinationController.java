package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.DestinationUseCase;
import com.example.attractionadvisor_backend.application.dto.DestinationDto;
import com.example.attractionadvisor_backend.presentation.request.DestinationCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<DestinationDto> createDestination(@RequestBody DestinationCreationRequest request) {
        DestinationDto destination = destinationUseCase.createDestination(
                request.getDestinationId(),
                request.getName(),
                request.getDescription(),
                request.getLatitude(),
                request.getLongitude()
        );
        return ResponseEntity.ok(destination);
    }
    @GetMapping("/{destinationId}")
    public ResponseEntity<DestinationDto> getDestination(@PathVariable String destinationId) {
        DestinationDto destination = destinationUseCase.getDestinationByDestinationId(destinationId);
        return ResponseEntity.ok(destination);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DestinationDto>> searchDestinations(@RequestParam String keyword, Pageable pageable) {
        Page<DestinationDto> destinations = destinationUseCase.searchDestinations(keyword, pageable);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<Page<DestinationDto>> getTopRatedDestinations(Pageable pageable) {
        Page<DestinationDto> destinations = destinationUseCase.getTopRatedDestinations(pageable);
        return ResponseEntity.ok(destinations);
    }
    @PutMapping("/{destinationId}")
    public ResponseEntity<DestinationDto> updateDestination(@PathVariable String destinationId, @RequestBody DestinationCreationRequest request) {
        DestinationDto updatedDestination = destinationUseCase.updateDestination(
                destinationId,
                request.getName(),
                request.getDescription(),
                request.getLatitude(),
                request.getLongitude()
        );
        return ResponseEntity.ok(updatedDestination);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable String destinationId) {
        destinationUseCase.deleteDestination(destinationId);
        return ResponseEntity.noContent().build();
    }
}