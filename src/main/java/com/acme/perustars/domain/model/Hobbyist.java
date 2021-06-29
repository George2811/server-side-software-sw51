package com.acme.perustars.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "hobbyists")
public class Hobbyist extends Person {
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "interests", joinColumns = {@JoinColumn(name = "hobbyist_id")},
                inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    private List<Specialty> interests;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "hobbyist_id")},
                inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private List<Artist> follows;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "favorite_artworks", joinColumns = {@JoinColumn(name = "hobbyist_id")},
                inverseJoinColumns = {@JoinColumn(name = "artwork_id")})
    private List<Artwork> favoriteArtworks;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "event_assistances", joinColumns = {@JoinColumn(name = "hobbyist_id")},
                inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> eventsAssistances;

    public Hobbyist() {
    }

    public Hobbyist(@NotBlank String firstName, @NotBlank String lastName, Long userId) {
        super(firstName, lastName, userId);
    }

    //Getters and Setters
    public List<Specialty> getInterests() {
        return interests;
    }

    public List<Artist> getFollows() {
        return follows;
    }

    public List<Artwork> getFavoriteArtworks() {
        return favoriteArtworks;
    }

    public List<Event> getEventsAssistances() {
        return eventsAssistances;
    }

    public boolean isSpecialty(Specialty specialty) {
        return this.getInterests().contains(specialty);
    }

    public Hobbyist selectSpecialty(Specialty specialty) {
        if (!this.isSpecialty(specialty))
            this.getInterests().add(specialty);
        return this;
    }

    public Hobbyist deselectSpecialty(Specialty specialty) {
        if (this.isSpecialty(specialty))
            this.getInterests().remove(specialty);
        return this;
    }

    public boolean isFollowing(Artist artist) {
        return this.getFollows().contains(artist);
    }

    public Hobbyist followArtist(Artist artist) {
        if (!this.isFollowing(artist))
            this.getFollows().add(artist);
        return this;
    }

    public Hobbyist unfollowArtist(Artist artist) {
        if (this.isFollowing(artist))
            this.getFollows().remove(artist);
        return this;
    }

    public boolean isFavoriteArtwork(Artwork artwork) {
        return this.getFavoriteArtworks().contains(artwork);
    }

    public Hobbyist selectArtwork(Artwork artwork) {
        if (!this.isFavoriteArtwork(artwork))
            this.getFavoriteArtworks().add(artwork);
        return this;
    }

    public Hobbyist deselectArtwork(Artwork artwork) {
        if (this.isFavoriteArtwork(artwork))
            this.getFavoriteArtworks().remove(artwork);
        return this;
    }

    public boolean isBooked(Event event) {
        return this.getEventsAssistances().contains(event);
    }

    public Hobbyist subscribeEvent(Event event) {
        if (!this.isBooked(event))
            this.getEventsAssistances().add(event);
        return this;
    }

    public Hobbyist unsubscribeEvent(Event event) {
        if (this.isBooked(event))
            this.getEventsAssistances().remove(event);
        return this;
    }
}
