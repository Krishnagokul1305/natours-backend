package com.example.Natours.repository;

import com.example.Natours.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends MongoRepository<Booking,String> {
    List<Booking> findByUser(String userId);
    List<Booking> findByTour(String tourId);
    Optional<Booking> findByUserAndTour(String userId, String tourId);
}
