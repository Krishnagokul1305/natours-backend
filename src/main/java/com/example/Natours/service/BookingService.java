package com.example.Natours.service;

import com.example.Natours.model.Booking;
import com.example.Natours.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return bookingRepository.findByUser(userId);
    }

    public List<Booking> getBookingsByTour(String tourId) {
        return bookingRepository.findByTour(tourId);
    }

    public Booking getBookingByUserAndTour(String userId, String tourId) {
        return bookingRepository.findByUserAndTour(userId, tourId).orElse(null);
    }

    public Booking updateBooking(String id, Map<String, Object> updates) {
        Booking existing = bookingRepository.findById(id)
                .orElse(null);
        if(existing == null) {
            return null;
        }
        updates.forEach((key, value) -> {
            switch (key) {
                case "tour" -> existing.setTour((String) value);
                case "user" -> existing.setUser((String) value);
                case "paid" -> existing.setPaid(Boolean.valueOf(value.toString()));
                case "paymentId" -> existing.setPaymentId((String) value);
            }
        });

        existing.setUpdatedAt();
        return bookingRepository.save(existing);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}