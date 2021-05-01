package com.perustars.events.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialties")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE},
    mappedBy = "specialties")
    private List<Hobbyist> hobbyists;

    //Getters and Setters
}

