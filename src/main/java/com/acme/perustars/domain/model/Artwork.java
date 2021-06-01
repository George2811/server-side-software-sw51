package com.acme.perustars.domain.model;

import com.acme.perustars.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "artworks")
public class Artwork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 250)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, scale = 2)
    private double cost;

    @Convert(converter = StringListConverter.class)
    private List<String> linkInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnore
    private Artist artist;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "favoriteArtworks")
    private List<Hobbyist> hobbyists;

    public Artwork() {
    }

    public Artwork(@NotBlank String title, @NotBlank String description, @NotBlank double cost, String linkInfo) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.linkInfo = Arrays.asList(linkInfo.split(","));
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public Artwork setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Artwork setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Artwork setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Artwork setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public List<String> getLinkInfo() {
        return linkInfo;
    }

    public Artwork setLinkInfo(String linkInfo) {
        this.linkInfo = Arrays.asList(linkInfo.split(","));
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public Artwork setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public List<Hobbyist> getHobbyists() {
        return hobbyists;
    }

    public Artwork setHobbyists(List<Hobbyist> hobbyists) {
        this.hobbyists = hobbyists;
        return this;
    }
}
