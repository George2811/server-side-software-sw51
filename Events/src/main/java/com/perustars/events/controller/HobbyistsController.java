package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(responseCode = "200", description = "All Hobbyist returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/hobbyists")
    public Page<HobbyistResource> getAllHobbyist(Pageable pageable){
        Page<Hobbyist> hobbyistsPage = hobbyistService.getAllHobbyists(pageable);
        List<HobbyistResource> resources = hobbyistsPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Hobbyist convertToEntity(SaveHobbyistResource resource){
        return mapper.map(resource, Hobbyist.class);
    }
    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
