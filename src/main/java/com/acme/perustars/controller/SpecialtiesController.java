package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.resource.SpecialtyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SpecialtiesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SpecialtyService specialtyService;



    @Operation(summary = "Get All Specialties", description = "Get All Specialties by Pages", tags = {"Specialties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Specialties returned", content =
            @Content(mediaType = "application/json"))
    })
    @GetMapping("/specialties")
    public Page<SpecialtyResource> getAllSpecialty(Pageable pageable) {
        Page<Specialty> specialtiesPage = specialtyService.getAllSpecialties(pageable);
        List<SpecialtyResource> resources = specialtiesPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Specialty by Id", description = "Get Specialty by Id", tags = {"Specialties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specialty returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/specialties/id/{specialtyId}")
    public SpecialtyResource getSpecialtyById(@PathVariable Long specialtyId) {
        return convertToResource(specialtyService.getSpecialtyById(specialtyId));
    }



    @Operation(summary = "Get Hobbyist by Name", description = "Get Hobbyist by Name", tags = {"Specialties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specialty returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/specialties/name/{specialtyName}")
    public SpecialtyResource getSpecialtyByName(@PathVariable String specialtyName) {
        return convertToResource(specialtyService.getSpecialtyByName(specialtyName));
    }



    private SpecialtyResource convertToResource(Specialty hobbyist) {
        return mapper.map(hobbyist, SpecialtyResource.class);
    }
}
