package com.example.attractionadvisor_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "destination_id", nullable = false)
    private String destinationId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private LocalDateTime createdAt;


 @Builder
    public Review(Long userId, String destinationId, String content, int rating, LocalDateTime createdAt) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }
    public void updateReview(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}