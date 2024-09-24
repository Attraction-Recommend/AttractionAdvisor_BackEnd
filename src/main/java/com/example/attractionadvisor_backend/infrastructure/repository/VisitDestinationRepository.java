package com.example.attractionadvisor_backend.infrastructure.repository;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitDestinationRepository extends JpaRepository<VisitDestination, Long> {
    List<VisitDestination> findByUserId(String userId);
    List<VisitDestination> findByDestinationId(String destinationId);
}