package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.application.dto.DestinationDto;
import com.example.attractionadvisor_backend.domain.entity.Destination;
import com.example.attractionadvisor_backend.domain.service.DestinationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinationUseCase {
    private final DestinationService destinationService;

    @Transactional
    public DestinationDto createDestination(String destinationId, String name, String description, Double latitude, Double longitude) {
        Destination destination = destinationService.createDestination(destinationId, name, description, latitude, longitude);
        return convertToDto(destination);
    }


    public Page<DestinationDto> searchDestinations(String keyword, Pageable pageable) {
        return destinationService.searchDestinations(keyword, pageable).map(this::convertToDto);
    }

    public Page<DestinationDto> getTopRatedDestinations(Pageable pageable) {
        return destinationService.getTopRatedDestinations(pageable).map(this::convertToDto);
    }
    //
    public DestinationDto getDestinationByDestinationId(String destinationId) {
        Destination destination = destinationService.getDestinationByDestinationId(destinationId);
        return convertToDto(destination);
    }

    @Transactional
    public DestinationDto updateDestination(String destinationId, String name, String description, Double latitude, Double longitude) {
        Destination updatedDestination = destinationService.updateDestination(destinationId, name, description, latitude, longitude);
        return convertToDto(updatedDestination);
    }

    @Transactional
    public void deleteDestination(String destinationId) {
        destinationService.deleteDestination(destinationId);
    }

    private DestinationDto convertToDto(Destination destination) {
        DestinationDto dto = new DestinationDto();
        dto.setDestinationId(destination.getDestinationId());
        dto.setName(destination.getName());
        dto.setDescription(destination.getDescription());
        dto.setLatitude(destination.getCoordinates().getX_coord());
        dto.setLongitude(destination.getCoordinates().getY_coord());
        return dto;
    }
}