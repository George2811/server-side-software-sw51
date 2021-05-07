package com.perustars.events.resource;

public class ArtworkResource {
    private Long id;
    private String title;
    private String description;
    private double cost;
    private String linkInfo;

    public Long getId() {
        return id;
    }

    public ArtworkResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArtworkResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtworkResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public ArtworkResource setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getLinkInfo() {
        return linkInfo;
    }

    public ArtworkResource setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
        return this;
    }
}
