package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.VisitUseCase;
import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.presentation.dto.VisitRecordRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {
    private final VisitUseCase visitUseCase;

    @PostMapping
    public ResponseEntity<VisitDestination> recordVisit(@RequestBody VisitRecordRequest request) {
        VisitDestination visit = visitUseCase.recordVisit(
                request.getUserId(),
                request.getDestinationId(),
                LocalDate.parse(request.getVisitStart()),
                LocalDate.parse(request.getVisitEnd())
        );
        return ResponseEntity.ok(visit);
    }
}