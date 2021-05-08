package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialtyService {
    Page<Specialty> getAllSpecialties(Pageable pageable);

    Page<Specialty> getAllSpecialtiesByHobbyistId(Long hobbyistId, Pageable pageable);

    Specialty getSpecialtyById(Long specialtyId);

    Specialty getSpecialtyByName(String specialtyName);
}
