package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.domain.service.VisitService;
import com.example.attractionadvisor_backend.infrastructure.repository.VisitDestinationRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitUseCase {
    private final VisitDestinationRepository visitDestinationRepository;
    private final VisitService visitService;

    @Transactional
    public VisitDestination recordVisit(String userId, String destinationId, LocalDate visitStart, LocalDate visitEnd) {
        VisitDestination visit = visitService.recordVisit(userId, destinationId, visitStart, visitEnd);
        return visitDestinationRepository.save(visit);
    }
}