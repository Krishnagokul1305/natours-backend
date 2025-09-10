package com.example.Natours.service;

import com.example.Natours.model.Booking;
import com.example.Natours.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByTour(String tourId) {
        return bookingRepository.findByTourId(tourId);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}