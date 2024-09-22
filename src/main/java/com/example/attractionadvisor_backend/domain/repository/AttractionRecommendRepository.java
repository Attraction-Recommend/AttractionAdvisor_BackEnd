package com.example.attractionadvisor_backend.domain.repository;

import com.example.attractionadvisor_backend.domain.entity.attraction.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRecommendRepository extends JpaRepository<Attraction, Long> {

}
