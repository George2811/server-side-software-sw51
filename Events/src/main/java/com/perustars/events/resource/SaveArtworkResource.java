package com.perustars.events.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveArtworkResource {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String title;

    @NotBlank
    @NotNull
    @Size(max = 250)
    private String description;

    @NotBlank
    @NotNull
    @Size(max = 6)
    private double cost;

    @NotNull
    @Lob
    private String linkInfo;

    public String getTitle() {
        return title;
    }

    public SaveArtworkResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveArtworkResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public SaveArtworkResource setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getLinkInfo() {
        return linkInfo;
    }

    public SaveArtworkResource setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
        return this;
    }
}
