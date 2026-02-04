package com.voyager.voyager_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voyager.voyager_backend.entity.Destination;
import com.voyager.voyager_backend.repository.DestinationRepository;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin
public class DestinationController {

    private final DestinationRepository destinationRepository;

    public DestinationController(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    // ✅ GET ALL DESTINATIONS
    @GetMapping
    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    // ✅ GET DESTINATION BY SLUG (FIXED)
    @GetMapping("/{slug}")
    public Destination getBySlug(@PathVariable String slug) {
        return destinationRepository
                .findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }
}
