package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Coordinates {


    @Column(name ="X_COORD")
    private Double x_coord;

    //세로
    @Column(name ="Y_COORD")
    private Double y_coord;

    @Builder
    public Coordinates(Double x_coord, Double y_coord){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }


}