package com.perustars.events.controller;

import com.perustars.events.domain.model.*;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import com.perustars.events.resource.SaveHobbyistResource;
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



    @Operation(summary = "Get Hobbyists", description = "Get All Hobbyists by Pages", tags = {"hobbyists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Hobbyists returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/hobbyists")
    public Page<HobbyistResource> getAllHobbyist(Pageable pageable){
        Page<Hobbyist> hobbyistsPage = hobbyistService.getAllHobbyists(pageable);
        List<HobbyistResource> resources = hobbyistsPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Hobbyist", description = "Get Hobbyist by Id", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}")
    public HobbyistResource getHobbyistById(Long hobbyistTd){
        return convertToResource(hobbyistService.getHobbyistById(hobbyistTd));
    }



    @Operation(summary = "Post Hobbyist", description = "Create a Hobbyist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/hobbyists")
    public HobbyistResource createHobbyist(@Valid @RequestBody SaveHobbyistResource resource){
        return convertToResource(hobbyistService.createHobbyist(convertToEntity(resource)));
    }



    @Operation(summary = "Put Hobbyist", description = "Update a Hobbyist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/hobbyists/{hobbyistId}")
    public HobbyistResource updateHobbyist(@PathVariable Long hobbyistId, @RequestBody SaveHobbyistResource resource){
        return convertToResource(hobbyistService.updateHobbyist(hobbyistId, convertToEntity(resource)));
    }



    @Operation(summary = "Delete Hobbyist", description = "Delete a Hobbyist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}")
    public ResponseEntity<?> deleteHobbyist(@PathVariable Long hobbyistId){
        return hobbyistService.deleteHobbyist(hobbyistId);
    }



    private Hobbyist convertToEntity(SaveHobbyistResource resource){
        return mapper.map(resource, Hobbyist.class);
    }
    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
