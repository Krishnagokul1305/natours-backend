package com.example.Natours.repository;

import com.example.Natours.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo  extends MongoRepository<Review,String> {
List<Review> findByTour(String tourId);
List<Review> findByUser(String userId);
}
