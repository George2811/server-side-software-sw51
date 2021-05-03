package com.perustars.events.domain.service;

import com.perustars.events.domain.model.Specialty;

import java.util.List;

public interface SpecialtyService {
    List<Specialty> getAllSpecialties();
    Specialty getSpecialtyById();
    Specialty getSpecialtyByName();
}
