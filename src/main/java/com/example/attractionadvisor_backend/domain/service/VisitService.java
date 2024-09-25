package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.domain.entity.VisitPeriod;
import com.example.attractionadvisor_backend.domain.exception.VisitNotFoundException;
import com.example.attractionadvisor_backend.infrastructure.repository.VisitDestinationRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitDestinationRepository visitDestinationRepository;

    public VisitDestination recordVisit(String userId, String destinationId, LocalDate visitStart, LocalDate visitEnd) {
        if (visitStart.isAfter(visitEnd)) {
            throw new IllegalArgumentException("Visit start date must be before or equal to end date.");
        }

        VisitPeriod visitPeriod = new VisitPeriod(visitStart, visitEnd);

        VisitDestination visit = VisitDestination.builder()
                .userId(userId)
                .destinationId(destinationId)
                .visitPeriod(visitPeriod)
                .build();

        return visitDestinationRepository.save(visit);
    }

    public VisitDestination getVisitById(Long visitId) {
        return visitDestinationRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFoundException("Visit with ID " + visitId + " not found."));
    }

    // Add other methods as needed (update, delete, etc.)
    public VisitDestination updateVisit(Long visitId, LocalDate visitStart, LocalDate visitEnd) {
        VisitDestination visit = getVisitById(visitId);
        VisitPeriod newVisitPeriod = new VisitPeriod(visitStart, visitEnd);
        visit.updateVisitPeriod(newVisitPeriod);
        return visitDestinationRepository.save(visit);
    }

    public void deleteVisit(Long visitId) {
        VisitDestination visit = getVisitById(visitId);
        visitDestinationRepository.delete(visit);
    }
}