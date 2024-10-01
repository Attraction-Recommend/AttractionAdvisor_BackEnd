package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "DESTINATION_ID", nullable = false)
    private String destinationId;

    private String destinationName;

    @Embedded
    private VisitPeriod visitPeriod;

    @Embedded
    private Coordinates coordinates;

    @Builder
    public VisitDestination(String userId, String destinationId, VisitPeriod visitPeriod, String destinationName, Coordinates coordinates) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.visitPeriod = visitPeriod;
        this.destinationName = destinationName;
        this.coordinates = coordinates;
    }

    public void updateVisitPeriod(VisitPeriod newVisitPeriod) {
        this.visitPeriod = newVisitPeriod;
    }
}