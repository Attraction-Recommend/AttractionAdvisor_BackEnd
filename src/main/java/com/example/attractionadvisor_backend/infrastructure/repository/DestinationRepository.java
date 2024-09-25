
package com.example.attractionadvisor_backend.infrastructure.repository;

import com.example.attractionadvisor_backend.domain.entity.Destination;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d WHERE " +
           "LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(d.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Destination> searchDestinations(String keyword, Pageable pageable);

    @Query("SELECT d FROM Destination d LEFT JOIN Review r ON d.destinationId = r.destinationId " +
            "GROUP BY d.id ORDER BY AVG(r.rating) DESC")
    Page<Destination> findTopRatedDestinations(Pageable pageable);

    Optional<Destination> findByDestinationId(String destinationId);
}
