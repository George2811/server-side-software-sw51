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
    public String getId() {
        return id;
    }

    public Hobbyist setId(String id) {
        this.id = id;
        return this;
    }
    public List<Specialty> getSpecialties() {
        return specialties;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public List<Artwork> getFavoriteArtworks() {
        return favoriteArtworks;
    }
    public List<Event> getEvents() {
        return events;
    }

    public boolean isSpecialty(Specialty specialty){
        return this.getSpecialties().contains(specialty);
    }
    public Hobbyist selectSpecialty(Specialty specialty){
        if (!this.isSpecialty(specialty))
            this.getSpecialties().add(specialty);
        return this;
    }
    public Hobbyist deselectSpecialty(Specialty specialty){
        if (this.isSpecialty(specialty))
            this.getSpecialties().remove(specialty);
        return this;
    }

    public boolean isFollowing(Artist artist){
        return this.getArtists().contains(artist);
    }
    public Hobbyist followArtist(Artist artist){
        if(!this.isFollowing(artist))
            this.getArtists().add(artist);
        return this;
    }
    public Hobbyist unfollowArtist(Artist artist){
        if (this.isFollowing(artist))
            this.getArtists().remove(artist);
        return this;
    }

    public boolean isFavoriteArtwork(Artwork artwork){
        return this.getFavoriteArtworks().contains(artwork);
    }
    public Hobbyist selectArtwork(Artwork artwork){
        if (!this.isFavoriteArtwork(artwork))
            this.getFavoriteArtworks().add(artwork);
        return this;
    }
    public Hobbyist deselectArtwork(Artwork artwork){
        if (this.isFavoriteArtwork(artwork))
            this.getFavoriteArtworks().remove(artwork);
        return this;
    }

    public boolean isBooked(Event event){
        return this.getEvents().contains(event);
    }
    public Hobbyist subscribeEvent(Event event){
        if (!this.isBooked(event))
            this.getEvents().add(event);
        return this;
    }
    public Hobbyist unsubscribeEvent(Event event){
        if (this.isBooked(event))
            this.getEvents().remove(event);
        return this;
    }
}
