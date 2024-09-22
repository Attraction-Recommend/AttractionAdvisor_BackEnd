package com.example.attractionadvisor_backend.init.csv.jpa;

import com.example.attractionadvisor_backend.domain.entity.attraction.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionCsvRepository extends JpaRepository<Attraction, Long> {
    // 필요한 경우 커스텀 메서드를 여기에 추가할 수 있습니다.
}