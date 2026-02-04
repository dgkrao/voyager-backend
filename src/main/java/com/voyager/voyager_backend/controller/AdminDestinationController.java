package com.voyager.voyager_backend.controller;

import com.voyager.voyager_backend.entity.Destination;
import com.voyager.voyager_backend.repository.DestinationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/destinations")
@CrossOrigin
public class AdminDestinationController {

    private final DestinationRepository repo;

    public AdminDestinationController(DestinationRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @PostMapping
    public Destination create(@RequestBody Destination d) {
        return repo.save(d);
    }

    // READ ALL
    @GetMapping
    public List<Destination> getAll() {
        return repo.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Destination update(@PathVariable Long id, @RequestBody Destination d) {
        Destination existing = repo.findById(id).orElseThrow();
        existing.setName(d.getName());
        existing.setSlug(d.getSlug());
        existing.setDescription(d.getDescription());
        existing.setImage(d.getImage());
        return repo.save(existing);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
