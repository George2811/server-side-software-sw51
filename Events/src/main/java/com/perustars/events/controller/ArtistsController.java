package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistsController {
    //@Autowired
    //private ModelMapper mapper; //no he agregado la dependencia
//    @Autowired
//    private ArtistService artistService;
//
//    @GetMapping("/artists")
//    public Page<ArtistResource> getAllArtists(Pageable pageable){
//        Page<Artist> artistPage = artistService.getAllArtists(pageable);
//        List<ArtistResource> resources = artistPage.getContent().
//                stream().map(this::convertToResource).
//                collect(Collector.ToList());
//        return new PageImpl<>(resources, pageable, resources.size());
//    }

    //FALTA RESOURCES Y MODEL MAPPER
}
