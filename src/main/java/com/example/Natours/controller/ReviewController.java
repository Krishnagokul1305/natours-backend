package com.example.Natours.controller;

import com.example.Natours.model.Review;
import com.example.Natours.service.ReviewService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Review>>> getReviews(){
        List<Review> reviews = reviewService.findAll();
        ApiResponse<List<Review>> res=new ApiResponse<>("success",reviews);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Review>> getReviewById(@PathVariable String id){
        Review review = reviewService.findById(id);
        if(review==null){
            ApiResponse<Review> res=new ApiResponse<>("error",null,"Review not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        return ResponseEntity.ok(new ApiResponse<>("success",review));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByUserId(@PathVariable String userId){
        List<Review> list=reviewService.findByUser(userId);
        ApiResponse<List<Review>> res=new ApiResponse<>("success",list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByTourId(@PathVariable String tourId){
        List<Review> list=reviewService.findByTour(tourId);
        ApiResponse<List<Review>> res=new ApiResponse<>("success",list);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Review>> addReview(@RequestBody Review review){
        Review savedReview = reviewService.save(review);
        ApiResponse<Review> res=new ApiResponse<>("success",savedReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Review>> updateReview(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {

        Review updated = reviewService.updateReview(id, updates);

        if(updated==null){
            ApiResponse<Review> res=new ApiResponse<>("error",null,"Review not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        ApiResponse<Review> response = new ApiResponse<>("success", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
