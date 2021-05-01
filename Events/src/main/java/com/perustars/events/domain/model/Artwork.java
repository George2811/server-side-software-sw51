package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artworks")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = true, length = 250)
    private String title;

    @Column(nullable = false, updatable = true, length = 255)
    private String description;

    @Column(nullable = false, updatable = true, scale = 2)
    private double cost;

    @Column(nullable = true, updatable = true)
    private String linkInfo; // should be x3

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnore
    private Artist artist;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "favoriteArtworks")
    private List<Hobbyist> hobbyists;

    //Getters and Setters
}
