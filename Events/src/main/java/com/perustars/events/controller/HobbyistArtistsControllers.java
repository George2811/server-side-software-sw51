package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.service.ArtistService;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.SaveArtistResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyistArtistsControllers {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/hobbyists/{hobbyistId}/artists")
    public Page<ArtistResource> getAllArtistByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Artist> artistPage = artistService.getAllArtistByHobbyistId(hobbyistId, pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Artist convertToEntity(SaveArtistResource resource) {
        return mapper.map(resource, Artist.class);
    }
    private ArtistResource convertToResource(Artist entity) {
        return mapper.map(entity, ArtistResource.class);
    }
}
