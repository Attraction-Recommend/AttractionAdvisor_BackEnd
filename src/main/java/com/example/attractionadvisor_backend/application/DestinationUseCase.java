package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.domain.entity.Destination;
import com.example.attractionadvisor_backend.infrastructure.repository.DestinationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinationUseCase {
    private final DestinationRepository destinationRepository;

    @Transactional
    public Destination addDestination(String destinationId,String name, String description, Double latitude, Double longitude) {
        Destination destination = Destination.builder()
                .destinationId(destinationId)
                .name(name)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        return destinationRepository.save(destination);
    }

    public Page<Destination> searchDestinations(String keyword, Pageable pageable) {
        return destinationRepository.searchDestinations(keyword, pageable);
    }

    public Page<Destination> getTopRatedDestinations(Pageable pageable) {
        return destinationRepository.findTopRatedDestinations(pageable);
    }
}