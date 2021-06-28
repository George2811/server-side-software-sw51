package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.resource.HobbyistResource;
import com.acme.perustars.resource.SaveHobbyistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HobbyistsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @Operation(summary = "Get All Hobbyists", description = "Get All Hobbyists by Pages", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Hobbyists returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists")
    public Page<HobbyistResource> getAllHobbyist(Pageable pageable) {
        Page<Hobbyist> hobbyistsPage = hobbyistService.getAllHobbyists(pageable);
        List<HobbyistResource> resources = hobbyistsPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Hobbyist by Id", description = "Get Hobbyist by Id", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/id/{hobbyistId}")
    public HobbyistResource getHobbyistById(@PathVariable Long hobbyistId) {
        return convertToResource(hobbyistService.getHobbyistById(hobbyistId));
    }



    @Operation(summary = "Get Hobbyist by User Id", description = "Get Hobbyist by User Id", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/userid/{userId}")
    public HobbyistResource getHobbyistByUserId(@PathVariable Long userId) {
        return convertToResource(hobbyistService.getHobbyistByUserId(userId));
    }



    @Operation(summary = "Post Hobbyist", description = "Create a Hobbyist", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists")
    public HobbyistResource createHobbyist(@Valid @RequestBody SaveHobbyistResource resource) {
        return convertToResource(hobbyistService.createHobbyist(convertToEntity(resource)));
    }



    @Operation(summary = "Put Hobbyist", description = "Update a Hobbyist", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/hobbyists/{hobbyistId}")
    public HobbyistResource updateHobbyist(@PathVariable Long hobbyistId, @RequestBody SaveHobbyistResource resource) {
        return convertToResource(hobbyistService.updateHobbyist(hobbyistId, convertToEntity(resource)));
    }



    @Operation(summary = "Delete Hobbyist", description = "Delete a Hobbyist", tags = {"Hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}")
    public ResponseEntity<?> deleteHobbyist(@PathVariable Long hobbyistId) {
        return hobbyistService.deleteHobbyist(hobbyistId);
    }



    private Hobbyist convertToEntity(SaveHobbyistResource resource) {
        return mapper.map(resource, Hobbyist.class);
    }

    private HobbyistResource convertToResource(Hobbyist hobbyist) {
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
