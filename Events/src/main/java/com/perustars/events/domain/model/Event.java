package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = true, nullable = false, length = 250)
    private String title;

    @Column(updatable = true, nullable = false, length = 255)
    private String description;

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateStart;

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEnd;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeOfEvent type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnore
    private Artist artist;

}
