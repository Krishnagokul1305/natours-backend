package com.example.Natours.repository;

import com.example.Natours.model.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepo extends MongoRepository<Tour,String> {
}
