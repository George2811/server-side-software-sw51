package com.acme.perustars.service;

import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.repository.SpecialtyRepository;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl2 implements SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private HobbyistRepository hobbyistRepository;

    @Override
    public Page<Specialty> getAllSpecialties(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Page<Specialty> getAllSpecialtiesByHobbyistId(Long hobbyistId, Pageable pageable) {
        return hobbyistRepository.findById(hobbyistId)
                .map(hobbyist -> {
                    List<Specialty> specialties = hobbyist.getSpecialties();
                    int specialtiesCount = specialties.size();
                    return new PageImpl<>(specialties, pageable, specialtiesCount);
                }).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Specialty getSpecialtyById(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty",
                "Id", specialtyId));
    }

    @Override
    public Specialty getSpecialtyByName(String specialtyName) {
        return specialtyRepository.findByName(specialtyName).orElseThrow(() -> new ResourceNotFoundException(
                "Specialty", "Name", specialtyName));
    }
}
