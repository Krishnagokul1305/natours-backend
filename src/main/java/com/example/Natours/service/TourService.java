package com.example.Natours.service;

import com.example.Natours.model.Tour;
import com.example.Natours.repository.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Tour updateTour(String id, Tour updatedTour) {
        Optional<Tour> existingTourOpt = tourRepo.findById(id);

        if (existingTourOpt.isPresent()) {
            Tour existingTour = existingTourOpt.get();

            // update fields (selectively or all)
            existingTour.setName(updatedTour.getName());
            existingTour.setDuration(updatedTour.getDuration());
            existingTour.setMaxGroupSize(updatedTour.getMaxGroupSize());
            existingTour.setDifficulty(updatedTour.getDifficulty());
            existingTour.setRatingsAverage(updatedTour.getRatingsAverage());
            existingTour.setRatingsQuantity(updatedTour.getRatingsQuantity());
            existingTour.setPrice(updatedTour.getPrice());
            existingTour.setPriceDiscount(updatedTour.getPriceDiscount());
            existingTour.setSummary(updatedTour.getSummary());
            existingTour.setDescription(updatedTour.getDescription());
            existingTour.setImageCover(updatedTour.getImageCover());
            existingTour.setImages(updatedTour.getImages());
            existingTour.setStartDates(updatedTour.getStartDates());
            existingTour.setSlug(updatedTour.getSlug());
            existingTour.setStartLocation(updatedTour.getStartLocation());
            existingTour.setLocations(updatedTour.getLocations());
            existingTour.setGuides(updatedTour.getGuides());

            return tourRepo.save(existingTour);
        } else {
            return null;
        }
    }

    public void  deleteTourById(String id){
        tourRepo.deleteById(id);
    }
}
