package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.Coordinates;
import com.example.attractionadvisor_backend.domain.entity.Destination;
import com.example.attractionadvisor_backend.domain.exception.DestinationNotFoundException;
import com.example.attractionadvisor_backend.infrastructure.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public Destination createDestination(String destinationId, String name, String description, Double latitude, Double longitude) {
        Coordinates coordinates = new Coordinates(latitude, longitude);

        Destination destination = Destination.builder()
                .destinationId(destinationId)
                .name(name)
                .description(description)
                .coordinates(coordinates)
                .build();
        return destinationRepository.save(destination);
    }

    public Destination getDestinationByDestinationId(String destinationId) {
        return destinationRepository.findByDestinationId(destinationId)
                .orElseThrow(() -> new DestinationNotFoundException("Destination with ID " + destinationId + " not found."));
    }

    // Add other methods as needed (update, delete, etc.)

    public Destination updateDestination(String destinationId, String name, String description, Double latitude, Double longitude) {
        Destination destination = getDestinationByDestinationId(destinationId);
        Coordinates newCoordinates = new Coordinates(latitude, longitude);
        destination.updateDetails(name, description, newCoordinates);
        return destinationRepository.save(destination);
    }

    public void deleteDestination(String destinationId) {
        Destination destination = getDestinationByDestinationId(destinationId);
        destinationRepository.delete(destination);
    }

    public Page<Destination> searchDestinations(String keyword, Pageable pageable) {
        return destinationRepository.searchDestinations(keyword, pageable);
    }

    public Page<Destination> getTopRatedDestinations(Pageable pageable) {
        return destinationRepository.findTopRatedDestinations(pageable);
    }
}