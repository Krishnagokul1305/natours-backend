package com.example.Natours.service;

import com.example.Natours.model.Review;
import com.example.Natours.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> findAll(){
        return reviewRepo.findAll();
    }

    public Review findById(String id){
        return reviewRepo.findById(id).orElse(null);
    }

    public List<Review> findByTour(String tour){
        return reviewRepo.findByTour(tour);
    }

    public List<Review> findByUser(String user){
        return reviewRepo.findByUser(user);
    }

    public Review updateReview(String id, Map<String, Object> updates) {
        Review existing = reviewRepo.findById(id)
                .orElse(null);

        if(existing == null){
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "review" -> existing.setReview((String) value);
                case "rating" -> existing.setRating(Double.valueOf(value.toString()));
                case "tour" -> existing.setTour((String) value);
                case "user" -> existing.setUser((String) value);
            }
        });

        existing.setUpdatedAt();
        return reviewRepo.save(existing);
    }

    public Review save(Review review){
        return reviewRepo.save(review);
    }

    public void  deleteById(String id){
        reviewRepo.deleteById(id);
    }
}
