package com.perustars.events.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSpecialityResource {
    @NotBlank
    @NotNull
    @Size(max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public SaveSpecialityResource setName(String name) {
        this.name = name;
        return this;
    }
}
