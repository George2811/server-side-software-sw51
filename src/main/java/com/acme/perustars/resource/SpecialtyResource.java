package com.acme.perustars.resource;

public class SpecialtyResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public SpecialtyResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpecialtyResource setName(String name) {
        this.name = name;
        return this;
    }
}
