package com.voyager.voyager_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Destination destination;

    private LocalDate startDate;
    private LocalDate endDate;

    private int travelers;
    private int amount;

    private String status;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Destination getDestination() {
        return destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getTravelers() {
        return travelers;
    }

    public int getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTravelers(int travelers) {
        this.travelers = travelers;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
