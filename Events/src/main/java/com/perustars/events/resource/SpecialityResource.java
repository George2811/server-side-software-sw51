package com.perustars.events.resource;

import com.perustars.events.domain.model.Specialty;

public class SpecialityResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public SpecialityResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpecialityResource setName(String name) {
        this.name = name;
        return this;
    }
}
