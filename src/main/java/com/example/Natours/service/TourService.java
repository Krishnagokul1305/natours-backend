package com.example.Natours.service;

import com.example.Natours.model.Tour;
import com.example.Natours.model.User;
import com.example.Natours.repository.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepo tourRepo;

    public List<Tour> getAllTours(){
        return tourRepo.findAll();
    }

    public Tour getTourById(String id){
        return tourRepo.findById(id).orElse(null);
    }

    public Tour createTour(Tour tour) {
        return tourRepo.save(tour);
    }

    public Tour updateTour(String id, Map<String, Object> updates) {
        Tour existingTour = tourRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> existingTour.setName((String) value);
                case "duration" -> existingTour.setDuration((Integer) value);
                case "maxGroupSize" -> existingTour.setMaxGroupSize((Integer) value);
                case "difficulty" -> existingTour.setDifficulty((String) value);
                case "ratingsAverage" -> existingTour.setRatingsAverage(Double.valueOf(value.toString()));
                case "ratingsQuantity" -> existingTour.setRatingsQuantity((Integer) value);
                case "price" -> existingTour.setPrice(Double.valueOf(value.toString()));
                case "priceDiscount" -> existingTour.setPriceDiscount(Double.valueOf(value.toString()));
                case "summary" -> existingTour.setSummary((String) value);
                case "description" -> existingTour.setDescription((String) value);
                case "imageCover" -> existingTour.setImageCover((String) value);
                case "images" -> existingTour.setImages((List<String>) value);
                case "startDates" -> existingTour.setStartDates((List<LocalDateTime>) value);
                case "slug" -> existingTour.setSlug((String) value);
                case "startLocation" -> existingTour.setStartLocation((Tour.Location) value);
                case "locations" -> existingTour.setLocations((List<Tour.Location>) value);
                case "guides" -> existingTour.setGuides((List<String>) value);
            }
        });

        return tourRepo.save(existingTour);
    }

    public void  deleteTourById(String id){
        tourRepo.deleteById(id);
    }
}
