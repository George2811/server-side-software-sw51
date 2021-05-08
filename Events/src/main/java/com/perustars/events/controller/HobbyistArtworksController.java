package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.model.Artwork;
import com.perustars.events.domain.service.ArtworkService;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.ArtworkResource;
import com.perustars.events.resource.SaveArtworkResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyistArtworksController {

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/hobbyists/{hobbyistId}/artworks")
    public Page<ArtworkResource> getAllArtworksByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworksByHobbyistId(hobbyistId, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Artwork convertToEntity(SaveArtworkResource resource){
        return mapper.map(resource, Artwork.class);
    }
    private ArtworkResource convertToResource(Artwork artwork){
        return mapper.map(artwork, ArtworkResource.class);

    }
}
