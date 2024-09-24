package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    public VisitDestination recordVisit(String userId, String destinationId, LocalDate visitStart, LocalDate visitEnd) {
        return VisitDestination.builder()
                .userId(userId)
                .destinationId(destinationId)
                .visitStart(visitStart)
                .visitEnd(visitEnd)
                .build();
    }
}
