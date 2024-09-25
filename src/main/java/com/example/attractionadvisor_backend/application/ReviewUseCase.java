package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.application.dto.ReviewDto;
import com.example.attractionadvisor_backend.domain.entity.Review;
import com.example.attractionadvisor_backend.domain.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewUseCase {
    private final ReviewService reviewService;


    @Transactional
    public ReviewDto createReview(Long userId, String destinationId, String content, int rating) {
        Review review = reviewService.createReview(userId, destinationId, content, rating);
        return convertToDto(review);
    }

    public ReviewDto getReviewById(Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        return convertToDto(review);
    }

    @Transactional
    public ReviewDto updateReview(Long reviewId, String content, int rating) {
        Review updatedReview = reviewService.updateReview(reviewId, content, rating);
        return convertToDto(updatedReview);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    public Page<ReviewDto> searchReviews(String keyword, Pageable pageable) {
        return reviewService.searchReviews(keyword, pageable).map(this::convertToDto);
    }

    public Double getAverageRating(String destinationId) {
        return reviewService.getAverageRating(destinationId);
    }
    public Long getReviewCount(String destinationId) {
        return reviewService.getReviewCount(destinationId);
    }
    private ReviewDto convertToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setUserId(review.getUserId());
        dto.setDestinationId(review.getDestinationId());
        dto.setContent(review.getContent());
        dto.setRating(review.getRating());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}