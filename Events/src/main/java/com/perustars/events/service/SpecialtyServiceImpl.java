package com.perustars.events.service;

import com.perustars.events.domain.model.Specialty;
import com.perustars.events.domain.repository.SpecialtyRepository;
import com.perustars.events.domain.service.SpecialtyService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Page<Specialty> getAllSpecialties(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Specialty getSpecialtyById(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", specialtyId));
    }

    @Override
    public Specialty getSpecialtyByName(String specialtyName) {
        return specialtyRepository.findByName(specialtyName).orElseThrow(() -> new ResourceNotFoundException("Specialty", "Name", specialtyName));
    }
}
