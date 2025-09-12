package com.example.Natours.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;
    private String tour;
    private String user;
    private Boolean paid = false;
    private String paymentId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
