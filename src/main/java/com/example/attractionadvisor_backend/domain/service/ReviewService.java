package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.Review;
import com.example.attractionadvisor_backend.domain.exception.InvalidReviewException;
import com.example.attractionadvisor_backend.domain.exception.ReviewNotFoundException;
import com.example.attractionadvisor_backend.infrastructure.repository.ReviewRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review createReview(Long userId, String destinationId, String content, int rating) {
        validateRating(rating);
        Review review = Review.builder()
                .userId(userId)
                .destinationId(destinationId)
                .content(content)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + reviewId + " not found."));
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


    // Add other methods as needed (update, delete, etc.)
    public Review updateReview(Long reviewId, String content, int rating) {
        Review review = getReviewById(reviewId);
        validateRating(rating);
        review.updateReview(content, rating);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        reviewRepository.delete(review);
    }
    private void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new InvalidReviewException("Rating must be between 1 and 5.");
        }
    }
}