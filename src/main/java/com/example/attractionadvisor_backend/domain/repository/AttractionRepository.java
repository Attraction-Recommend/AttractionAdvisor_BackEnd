package com.example.attractionadvisor_backend.domain.repository;

import com.example.attractionadvisor_backend.domain.entity.attraction.Attraction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByTravelId(String travelId);

    List<Attraction> findByPoiId(String poiId);


}