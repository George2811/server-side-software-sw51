package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.resource.ArtistResource;
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
public class FollowsControllers {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ArtistService artistService;


    @Operation(summary = "Get All Followed Artists by Hobbyist Id", description = "Get All Followed Artists by Hobbyist Id per Page", tags = {"Follows"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Artists returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}/artists")
    public Page<ArtistResource> getAllArtistByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Artist> artistPage = artistService.getAllArtistByHobbyistId(hobbyistId, pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    private ArtistResource convertToResource(Artist entity) {
        return mapper.map(entity, ArtistResource.class);
    }
}
