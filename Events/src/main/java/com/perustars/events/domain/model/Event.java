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
}
