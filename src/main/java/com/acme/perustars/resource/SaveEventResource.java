package com.acme.perustars.resource;

import com.acme.perustars.domain.model.TypeOfEvent;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @NotNull
    private double cost;

    @NotNull
    private Calendar dateStart;

    @NotNull
    private Calendar dateEnd;

    @NotNull
    private TypeOfEvent type;

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

    public TypeOfEvent getType() {
        return type;
    }

    public SaveEventResource setType(TypeOfEvent type) {
        this.type = type;
        return this;
    }
}
