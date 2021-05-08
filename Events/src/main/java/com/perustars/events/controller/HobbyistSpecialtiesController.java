package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.model.Specialty;
import com.perustars.events.domain.service.SpecialtyService;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.SpecialtyResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyistSpecialtiesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/hobbyists/{hobbyistId}/specialties")
    public Page<SpecialtyResource> getAllSpecialtiesByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Specialty> specialtyPage = specialtyService.getAllSpecialtiesByHobbyistId(hobbyistId, pageable);
        List<SpecialtyResource> resources = specialtyPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    private SpecialtyResource convertToResource(Specialty hobbyist){
        return mapper.map(hobbyist, SpecialtyResource.class);
    }
}
