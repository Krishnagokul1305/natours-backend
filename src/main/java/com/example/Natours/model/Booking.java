package com.example.Natours.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;

    @DBRef
    private Tour tour;

    @DBRef
    private User user;

    private Boolean paid = false;

    private String paymentId;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
