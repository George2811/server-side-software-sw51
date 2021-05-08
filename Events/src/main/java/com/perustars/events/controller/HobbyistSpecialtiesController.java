package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HobbyistSpecialtiesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @PostMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource associateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId){
        return convertToResource(hobbyistService.associateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }



    @DeleteMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource disassociateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId){
        return convertToResource(hobbyistService.disassociateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
