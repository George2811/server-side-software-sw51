package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(updatable = true, nullable = false, length = 250)
    private String title;

    @NotBlank
    @Column(updatable = true, nullable = false, length = 255)
    private String description;

    @NotBlank
    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateStart;

    @NotBlank
    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEnd;

    @NotBlank
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeOfEvent type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnore
    private Artist artist;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "events")
    private List<Hobbyist> hobbyists;

    public Event() {
    }

    public Event(@NotBlank String title, @NotBlank String description, @NotBlank Calendar dateStart, @NotBlank Calendar dateEnd, @NotBlank TypeOfEvent type) {
        this.title = title;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public Event setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public Event setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public Event setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public TypeOfEvent getType() {
        return type;
    }

    public Event setType(TypeOfEvent type) {
        this.type = type;
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public Event setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public List<Hobbyist> getHobbyists() {
        return hobbyists;
    }

    public Event setHobbyists(List<Hobbyist> hobbyists) {
        this.hobbyists = hobbyists;
        return this;
    }
}
