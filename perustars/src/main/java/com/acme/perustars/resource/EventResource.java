package com.acme.perustars.resource;

import java.util.Calendar;

public class EventResource {
    private Long id;
    private String title;
    private String description;
    private Calendar dateStart;
    private Calendar dateEnd;
    private double cost;

    public Long getId() {
        return id;
    }

    public EventResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EventResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public EventResource setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public EventResource setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public EventResource setCost(double cost) {
        this.cost = cost;
        return this;
    }
}
