package com.voyager.voyager_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "destinations")
@Data
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;
}
