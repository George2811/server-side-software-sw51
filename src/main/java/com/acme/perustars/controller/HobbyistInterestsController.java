package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.resource.HobbyistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HobbyistInterestsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;

    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Specialty", tags =
            {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource associateHobbyistWithSpecialty(@PathVariable Long hobbyistId,
                                                           @PathVariable Long specialtyId) {
        return convertToResource(hobbyistService.associateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }

    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Specialty",
            tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/specialties/{specialtyId}")
    public HobbyistResource disassociateHobbyistWithSpecialty(@PathVariable Long hobbyistId,
                                                              @PathVariable Long specialtyId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithSpecialty(hobbyistId, specialtyId));
    }

    private HobbyistResource convertToResource(Hobbyist hobbyist) {
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
