package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.domain.entity.Review;
import com.example.attractionadvisor_backend.domain.service.ReviewService;
import com.example.attractionadvisor_backend.infrastructure.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewUseCase {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @Transactional
    public Review addReview(Long userId, String destinationId, String content, int rating) {
        Review review = reviewService.createReview(userId, destinationId, content, rating);
        return reviewRepository.save(review);
    }

    public Page<Review> searchReviews(String keyword, Pageable pageable) {
        return reviewRepository.searchReviews(keyword, pageable);
    }

    public Double getAverageRating(String destinationId) {
        return reviewRepository.getAverageRatingForDestination(destinationId);
    }

    public Long getReviewCount(String destinationId) {
        return reviewRepository.getReviewCountForDestination(destinationId);
    }
}