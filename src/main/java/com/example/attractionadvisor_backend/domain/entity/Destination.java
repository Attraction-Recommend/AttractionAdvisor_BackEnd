package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
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
@Table(name = "destinations")
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POI_ID")
    private String destinationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "X_COORD")
    private Double latitude;

    @Column(name = "Y_COORD")
    private Double longitude;

    @Builder
    public Destination(String destinationId,String name, String description, Double latitude, Double longitude) {
        this.destinationId  = destinationId;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
