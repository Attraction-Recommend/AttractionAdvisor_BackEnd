package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {


    @Column(name ="X_COORD")
    private Double latitude;

    //세로
    @Column(name ="Y_COORD")
    private Double longitude;

    @Builder
    public Address(Double latitude,Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


}