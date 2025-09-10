package com.example.Natours.controller;

import com.example.Natours.model.Tour;
import com.example.Natours.service.TourService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    @Autowired
    private TourService tourService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Tour>>> getAllTours() {
        List<Tour> tours = tourService.getAllTours();
        ApiResponse<List<Tour>>res=new ApiResponse<>("success",tours);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTourById(@PathVariable String id) {
        Tour tour = tourService.getTourById(id);
        if(tour==null){
            return  ResponseEntity.status(404).body(new ApiResponse<>("error", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success",tour));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Tour>> addTour(@RequestBody Tour tour) {
        Tour newTour=tourService.createTour(tour);
        ApiResponse<Tour>res=new ApiResponse<>("success",newTour);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Tour>> updateTour(@RequestBody Tour tour) {
        Tour updated=new Tour();
        ApiResponse<Tour>res=new ApiResponse<>("success",updated);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTourById(@PathVariable String id) {
        tourService.deleteTourById(id);
        return ResponseEntity.noContent().build();
    }
}
