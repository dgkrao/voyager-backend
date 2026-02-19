package com.voyager.voyager_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.voyager.voyager_backend.dto.BookingRequest;
import com.voyager.voyager_backend.entity.Booking;
import com.voyager.voyager_backend.entity.Destination;
import com.voyager.voyager_backend.entity.User;
import com.voyager.voyager_backend.repository.BookingRepository;
import com.voyager.voyager_backend.repository.DestinationRepository;
import com.voyager.voyager_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    private final BookingRepository bookingRepository;
    private final DestinationRepository destinationRepository;
    private final UserRepository userRepository;

    public BookingController(
            BookingRepository bookingRepository,
            DestinationRepository destinationRepository,
            UserRepository userRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.destinationRepository = destinationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Destination destination = destinationRepository.findBySlug(request.getDestinationSlug()).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDestination(destination);
        booking.setStartDate(LocalDate.parse(request.getStartDate()));
        booking.setEndDate(LocalDate.parse(request.getEndDate()));
        booking.setTravelers(request.getTravelers());
        booking.setAmount(50000 * request.getTravelers());
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    @GetMapping
    public List<Booking> getMyBookings() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return bookingRepository.findByUserEmail(email);
    }
}
