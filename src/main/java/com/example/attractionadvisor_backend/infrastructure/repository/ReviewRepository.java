package com.example.attractionadvisor_backend.infrastructure.repository;

import com.example.attractionadvisor_backend.domain.entity.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDestinationId(String destinationId);
    List<Review> findByUserId(Long userId);

    @Query("SELECT r FROM Review r WHERE LOWER(r.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Review> searchReviews(String keyword, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.destinationId = :destinationId")
    Double getAverageRatingForDestination(String destinationId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.destinationId = :destinationId")
    Long getReviewCountForDestination(String destinationId);
}
