package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//maybe this attribute should be in the User Entity

    @Column(updatable = true, nullable = true)
    private String brandName;
    @Column(updatable = true,nullable = false,length = 255)
    private String description;
    @Column(updatable = true,nullable = false, length = 100)
    private String phrase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    @JsonIgnore
    private Specialty specialtyArt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "artists")
    private List<Hobbyist> hobbyists;

    //Getters and Setters
}
