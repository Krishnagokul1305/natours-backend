package com.example.Natours.controller;

import com.example.Natours.model.Booking;
import com.example.Natours.service.BookingService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByTour(@PathVariable String tourId) {
        List<Booking> bookings = bookingService.getBookingsByTour(tourId);
        return ResponseEntity.ok(new ApiResponse<>("success", bookings));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByUserId(@PathVariable String userId) {
        List<Booking> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(new ApiResponse<>("success", bookings));
    }

    @GetMapping("/tour/{tourId}/user/{userId}")
    public ResponseEntity<ApiResponse<Booking>> getBookingsByTour(@PathVariable String tourId, @PathVariable String userId) {
        Booking booking=bookingService.getBookingByUserAndTour(userId,tourId);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", null,"booking not found"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", booking));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", newBooking));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> updateBooking(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Booking updated = bookingService.updateBooking(id, updates);
        if(updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", null,"booking not found"));
        }
        ApiResponse<Booking> res = new ApiResponse<>("success", updated);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}