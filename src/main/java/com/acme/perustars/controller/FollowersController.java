package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.resource.ArtistResource;
import com.acme.perustars.resource.HobbyistResource;
import com.acme.perustars.resource.SaveArtistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FollowersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;
    @Autowired
    private ArtistService artistService;


    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Artist", tags = {
            "hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource associateHobbyistWithArtist(@PathVariable Long hobbyistId, @PathVariable Long artistId) {
        return convertToResource(hobbyistService.associateHobbyistWithArtist(hobbyistId, artistId));
    }

    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Artist", tags
            = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource disassociateHobbyistWithArtist(@PathVariable Long hobbyistId, @PathVariable Long artistId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithArtist(hobbyistId, artistId));
    }

    private Artist convertToEntity(SaveArtistResource resource) {
        return mapper.map(resource, Artist.class);
    }

    private ArtistResource convertToResource(Artist entity) {
        return mapper.map(entity, ArtistResource.class);
    }

    private HobbyistResource convertToResource(Hobbyist entity) {
        return mapper.map(entity, HobbyistResource.class);
    }
}
