package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.Review;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public Review createReview(Long userId, String destinationId, String content, int rating) {
        return Review.builder()
                .userId(userId)
                .destinationId(destinationId)
                .content(content)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
