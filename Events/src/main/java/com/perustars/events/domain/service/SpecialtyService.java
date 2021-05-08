package com.perustars.events.domain.service;

import com.perustars.events.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecialtyService {
    Page<Specialty> getAllSpecialties(Pageable pageable);
    Specialty getSpecialtyById(Long specialtyId);
    Specialty getSpecialtyByName(String specialtyName);
}
