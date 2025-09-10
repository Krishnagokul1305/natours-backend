package com.example.Natours.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;

    private String review;
    private Double rating;
    private LocalDateTime createdAt = LocalDateTime.now();

    @DBRef
    private Tour tour;

    @DBRef
    private User user;
}