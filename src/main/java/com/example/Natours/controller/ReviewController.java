package com.example.Natours.controller;

import com.example.Natours.model.Review;
import com.example.Natours.service.ReviewService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            ApiResponse<Review> res=new ApiResponse<>("error",null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        return ResponseEntity.ok(new ApiResponse<>("success",review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
