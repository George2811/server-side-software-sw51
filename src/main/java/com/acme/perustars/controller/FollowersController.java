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
@CrossOrigin
public class FollowersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @Operation(summary = "Post Follower Association", description = "Associate a Hobbyist with an Artist", tags = {"Followers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource associateHobbyistWithArtist(@PathVariable Long hobbyistId, @PathVariable Long artistId) {
        return convertToResource(hobbyistService.associateHobbyistWithArtist(hobbyistId, artistId));
    }



    @Operation(summary = "Delete Follower Association", description = "Disassociate a Hobbyist with an Artist", tags = {"Followers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource disassociateHobbyistWithArtist(@PathVariable Long hobbyistId, @PathVariable Long artistId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithArtist(hobbyistId, artistId));
    }



    private HobbyistResource convertToResource(Hobbyist entity) {
        return mapper.map(entity, HobbyistResource.class);
    }
}
