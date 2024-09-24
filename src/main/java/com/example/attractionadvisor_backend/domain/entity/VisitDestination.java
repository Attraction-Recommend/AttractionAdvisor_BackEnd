package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "visit_destination")
@NoArgsConstructor
public class VisitDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRAVEL_ID")
    private String userId;

    @Column(name = "POI_ID")
    private String destinationId;

    @Column(name = "VISIT_START_YMD")
    private LocalDate visitStart;

    @Column(name = "VISIT_END_YMD")
    private LocalDate visitEnd;

    @Builder
    public VisitDestination(String userId, String destinationId, LocalDate visitStart, LocalDate visitEnd) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
    }

    public LocalDate getVisitStartDate() {
        return this.visitStart;
    }

    public LocalDate getVisitEndDate() {
        return this.visitEnd;
    }
}