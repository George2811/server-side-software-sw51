package com.perustars.events.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class SaveEventResource {
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

    @NotBlank
    @NotNull
    private Calendar dateStart;

    @NotBlank
    @NotNull
    private Calendar dateEnd;

    public String getTitle() {
        return title;
    }

    public SaveEventResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveEventResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public SaveEventResource setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getLinkInfo() {
        return linkInfo;
    }

    public SaveEventResource setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
        return this;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public SaveEventResource setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public SaveEventResource setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }
}
