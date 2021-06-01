package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ArtistService {
    Page<Artist> getAllArtists(Pageable pageable);
    Artist getArtistById(Long artistId);
    Artist createArtist(Artist artist);
    Artist updateArtist(Long artistId, Artist artistRequest);
    ResponseEntity<?> deleteArtist(Long artistId);
    Page<Artist> getAllArtistsByBrandName(String BrandName, Pageable pageable);
    Page<Artist> getAllArtistByHobbyistId(Long hobbyistId, Pageable pageable);
}
