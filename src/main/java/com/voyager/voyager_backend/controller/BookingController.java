package com.voyager.voyager_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // ✅ CREATE BOOKING (FINAL FIX)
    @PostMapping
    public Booking createBooking(
            @RequestBody BookingRequest request,
            @RequestParam String email
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Destination destination = destinationRepository
                .findBySlug(request.getDestinationSlug())
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDestination(destination); // ✅ NEVER NULL
        booking.setStartDate(LocalDate.parse(request.getStartDate()));
        booking.setEndDate(LocalDate.parse(request.getEndDate()));
        booking.setTravelers(request.getTravelers());
        booking.setAmount(50000 * request.getTravelers());
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // ✅ GET USER BOOKINGS
    @GetMapping
    public List<Booking> getMyBookings(@RequestParam String email) {
        return bookingRepository.findByUserEmail(email);
    }
    // 🔹 ADMIN: GET ALL BOOKINGS
@GetMapping("/admin")
public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
}

// 🔹 ADMIN: UPDATE STATUS
@PostMapping("/admin/status")
public Booking updateStatus(
        @RequestParam Long bookingId,
        @RequestParam String status
) {
    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    booking.setStatus(status);
    return bookingRepository.save(booking);
}

}
