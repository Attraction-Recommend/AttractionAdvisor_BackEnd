package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.ReviewUseCase;
import com.example.attractionadvisor_backend.application.dto.ReviewDto;
import com.example.attractionadvisor_backend.presentation.request.ReviewCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewUseCase reviewUseCase;

    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewCreationRequest request) {
        ReviewDto reviewDto = reviewUseCase.createReview(
                request.getUserId(),
                request.getDestinationId(),
                request.getContent(),
                request.getRating()
        );
        return ResponseEntity.ok(reviewDto);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long reviewId) {
        ReviewDto review = reviewUseCase.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long reviewId, @RequestBody ReviewCreationRequest request) {
        ReviewDto updatedReview = reviewUseCase.updateReview(reviewId, request.getContent(), request.getRating());
        return ResponseEntity.ok(updatedReview);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewUseCase.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ReviewDto>> searchReviews(@RequestParam String keyword, Pageable pageable) {
        Page<ReviewDto> reviews = reviewUseCase.searchReviews(keyword, pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/destinations/{destinationId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable String destinationId) {
        Double averageRating = reviewUseCase.getAverageRating(destinationId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/destinations/{destinationId}/review-count")
    public ResponseEntity<Long> getReviewCount(@PathVariable String destinationId) {
        Long reviewCount = reviewUseCase.getReviewCount(destinationId);
        return ResponseEntity.ok(reviewCount);
    }

}
