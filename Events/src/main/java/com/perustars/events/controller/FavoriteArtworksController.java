package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteArtworksController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @PostMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource associateHobbyistWithArtwork(@PathVariable Long hobbyistId,@PathVariable Long artworkId){
        return convertToResource(hobbyistService.associateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    @DeleteMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource disassociateHobbyistWithArtwork(@PathVariable Long hobbyistId,@PathVariable Long artworkId){
        return convertToResource(hobbyistService.disassociateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
