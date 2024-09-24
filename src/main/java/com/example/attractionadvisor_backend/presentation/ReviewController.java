package com.example.attractionadvisor_backend.presentation;

import com.example.attractionadvisor_backend.application.ReviewUseCase;
import com.example.attractionadvisor_backend.domain.entity.Review;
import com.example.attractionadvisor_backend.presentation.dto.ReviewCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Review> addReview(@RequestBody ReviewCreationRequest request) {
        Review review = reviewUseCase.addReview(
                request.getUserId(),
                request.getDestinationId(),
                request.getContent(),
                request.getRating()
        );
        return ResponseEntity.ok(review);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Review>> searchReviews(
            @RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(reviewUseCase.searchReviews(keyword, pageable));
    }

    @GetMapping("/destinations/{destinationId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable String destinationId) {
        return ResponseEntity.ok(reviewUseCase.getAverageRating(destinationId));
    }

    @GetMapping("/destinations/{destinationId}/review-count")
    public ResponseEntity<Long> getReviewCount(@PathVariable String  destinationId) {
        return ResponseEntity.ok(reviewUseCase.getReviewCount(destinationId));
    }
}
