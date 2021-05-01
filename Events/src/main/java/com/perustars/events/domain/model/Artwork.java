package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "artworks")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, updatable = true, length = 250)
    private String title;

    @NotBlank
    @Column(nullable = false, updatable = true, length = 255)
    private String description;

    @NotBlank
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

    public Artwork() {
    }

    public Artwork(@NotBlank String title, @NotBlank String description, @NotBlank double cost, String linkInfo) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.linkInfo = linkInfo;
    }

    //Getters and Setters
}
