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
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String tour;
    private String user;

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}