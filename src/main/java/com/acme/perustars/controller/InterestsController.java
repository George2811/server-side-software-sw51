package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.domain.service.SpecialtyService;
import com.acme.perustars.resource.HobbyistResource;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InterestsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;
    @Autowired
    private SpecialtyService specialtyService;

    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Specialty", tags = {"Interests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource associateHobbyistWithSpecialty(@PathVariable Long hobbyistId,
                                                           @PathVariable Long specialtyId) {
        return convertToResource(hobbyistService.associateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }



    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Specialty", tags = {"Interests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource disassociateHobbyistWithSpecialty(@PathVariable Long hobbyistId,
                                                              @PathVariable Long specialtyId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }



    @Operation(summary = "Get All Specialties by HobbyistId", description = "Get All Specialties by HobbyistId", tags = {"Interests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specialties returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}/specialties")
    public Page<SpecialtyResource> getAllSpecialtiesByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Specialty> specialtyPage = specialtyService.getAllSpecialtiesByHobbyistId(hobbyistId, pageable);
        List<SpecialtyResource> resources = specialtyPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist) {
        return mapper.map(hobbyist, HobbyistResource.class);
    }

    private SpecialtyResource convertToResource(Specialty hobbyist) {
        return mapper.map(hobbyist, SpecialtyResource.class);
    }
}
