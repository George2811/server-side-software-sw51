package com.perustars.events.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "hobbyists")
public class Hobbyist extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;//maybe this attribute should be in the User Entity

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "hobbyist_specialty",
            joinColumns = {@JoinColumn(name = "hobbyist_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    private List<Specialty> specialties;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "hobbyist_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private List<Artist> artists;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "favorite_artworks",
            joinColumns = {@JoinColumn(name = "hobbyist_id")},
            inverseJoinColumns = {@JoinColumn(name = "artwork_id")})
    private List<Artwork> favoriteArtworks;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "booking",
            joinColumns = {@JoinColumn(name = "hobbyist_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> events;

    public Hobbyist(){

    }
    public Hobbyist(@NotBlank String firstName, @NotBlank String lastName) {
        super(firstName, lastName);
    }
//Getters and Setters
}
