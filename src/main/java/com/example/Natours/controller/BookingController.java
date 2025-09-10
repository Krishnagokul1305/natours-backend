package com.example.Natours.controller;

import com.example.Natours.model.Booking;
import com.example.Natours.service.BookingService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        return ResponseEntity.ok(new ApiResponse<>("success", bookingService.getAllBookings()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", booking));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", newBooking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}