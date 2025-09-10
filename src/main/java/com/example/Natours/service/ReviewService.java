package com.example.Natours.service;

import com.example.Natours.model.Review;
import com.example.Natours.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    public Review save(Review review){
        return reviewRepo.save(review);
    }

    public void  deleteById(String id){
        reviewRepo.deleteById(id);
    }
}
