package com.example.attractionadvisor_backend.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class VisitPeriod {
    @Column(name = "VISIT_START_YMD", nullable = false)
    private LocalDate visitStart;

    @Column(name = "VISIT_END_YMD", nullable = false)
    private LocalDate visitEnd;

    @Builder
    public VisitPeriod(LocalDate visitStart, LocalDate visitEnd) {
        if (visitStart.isAfter(visitEnd)) {
            throw new IllegalArgumentException("Visit start date must be before or equal to end date.");
        }
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
    }
}
