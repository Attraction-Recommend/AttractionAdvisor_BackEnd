package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.application.dto.VisitDestinationDto;
import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.domain.service.VisitService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitUseCase {
    private final VisitService visitService;

    @Transactional
    public VisitDestinationDto recordVisit(String userId, String destinationId, LocalDate visitStart, LocalDate visitEnd) {
        VisitDestination visit = visitService.recordVisit(userId, destinationId, visitStart, visitEnd);
        return convertToDto(visit);
    }

    //
    public VisitDestinationDto getVisitById(Long visitId) {
        VisitDestination visit = visitService.getVisitById(visitId);
        return convertToDto(visit);
    }

    @Transactional
    public VisitDestinationDto updateVisit(Long visitId, LocalDate visitStart, LocalDate visitEnd) {
        VisitDestination updatedVisit = visitService.updateVisit(visitId, visitStart, visitEnd);
        return convertToDto(updatedVisit);
    }

    @Transactional
    public void deleteVisit(Long visitId) {
        visitService.deleteVisit(visitId);
    }

    private VisitDestinationDto convertToDto(VisitDestination visit) {
        VisitDestinationDto dto = new VisitDestinationDto();
        dto.setId(visit.getId());
        dto.setUserId(visit.getUserId());
        dto.setDestinationId(visit.getDestinationId());
        dto.setVisitStart(visit.getVisitPeriod().getVisitStart());
        dto.setVisitEnd(visit.getVisitPeriod().getVisitEnd());
        return dto;
    }
}