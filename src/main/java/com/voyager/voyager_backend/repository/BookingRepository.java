package com.voyager.voyager_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voyager.voyager_backend.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserEmail(String email);
}
