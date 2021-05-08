package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HobbyistArtworksController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @PostMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource associateHobbyistWithArtwork(Long hobbyistId, Long artworkId){
        return convertToResource(hobbyistService.associateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    @DeleteMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource disassociateHobbyistWithArtwork(Long hobbyistId, Long artworkId){
        return convertToResource(hobbyistService.disassociateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
