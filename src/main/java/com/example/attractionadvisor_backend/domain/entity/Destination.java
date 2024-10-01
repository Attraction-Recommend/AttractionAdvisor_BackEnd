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
@Table(name = "destinations")
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESTINATION_ID", unique = true)
    private String destinationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
    @Embedded
    private Coordinates coordinates;

    @Builder
    public Destination(String destinationId, String name, String description, Coordinates coordinates) {
        this.destinationId = destinationId;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
    }

    public void updateDetails(String name, String description, Coordinates coordinates) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
    }
}
