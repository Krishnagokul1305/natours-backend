package com.example.Natours.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tours")
@Data
public class Tour {

    @Id
    private String id;

    private String name;
    private Integer duration;
    private Integer maxGroupSize;
    private String difficulty;

    private Double ratingsAverage = 0.0;
    private Integer ratingsQuantity = 0;

    private Double price;
    private Double priceDiscount;

    private String summary;
    private String description;
    private String imageCover;
    private List<String> images;

    private LocalDateTime createdAt = LocalDateTime.now();
    private List<LocalDateTime> startDates;

    private String slug;

    private Location startLocation;
    private List<Location> locations;

    @DBRef
    private List<String> guides;

    @Data
    public static class Location {
        private String type = "Point";
        private List<Double> coordinates;
        private String address;
        private String description;
        private Integer day;

    }
}
