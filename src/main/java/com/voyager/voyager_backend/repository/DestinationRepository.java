package com.voyager.voyager_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voyager.voyager_backend.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findById(Long id);

    Optional<Destination> findBySlug(String slug);
}
