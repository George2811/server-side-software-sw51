package com.perustars.events.domain.model;

import javax.persistence.*;
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

}
