package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.VisitUseCase;
import com.example.attractionadvisor_backend.application.dto.VisitDestinationDto;
import com.example.attractionadvisor_backend.presentation.request.VisitRecordRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<VisitDestinationDto> recordVisit(@RequestBody VisitRecordRequest request) {
        VisitDestinationDto visit = visitUseCase.recordVisit(
                request.getUserId(),
                request.getDestinationId(),
                LocalDate.parse(request.getVisitStart()),
                LocalDate.parse(request.getVisitEnd())
        );
        return ResponseEntity.ok(visit);
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<VisitDestinationDto> getVisit(@PathVariable Long visitId) {
        VisitDestinationDto visit = visitUseCase.getVisitById(visitId);
        return ResponseEntity.ok(visit);
    }
}