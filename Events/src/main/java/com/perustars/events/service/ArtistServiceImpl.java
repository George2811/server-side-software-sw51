package com.perustars.events.service;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.repository.ArtistRepository;
import com.perustars.events.domain.repository.HobbyistRepository;
import com.perustars.events.domain.service.ArtistService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private HobbyistRepository hobbyistRepository;

    @Override
    public Page<Artist> getAllArtists(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtist(Long artistId, Artist artistRequest) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
        artist.setBrandName(artistRequest.getBrandName())
                .setDescription(artistRequest.getDescription())
                .setPhrase(artistRequest.getPhrase())
                .setSpecialtyArt(artistRequest.getSpecialtyArt())
                .setFirstName(artistRequest.getFirstName())
                .setLastName(artistRequest.getLastName());
        return artistRepository.save(artist);
    }

    @Override
    public ResponseEntity<?> deleteArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
        artistRepository.delete(artist);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Artist> getAllArtistsByBrandName(String brandName, Pageable pageable) {
        return artistRepository.findByBrandName(brandName, pageable);
    }

    @Override
    public Page<Artist> getAllArtistByHobbyistId(Long hobbyistId, Pageable pageable) {
        return hobbyistRepository.findById(hobbyistId)
                .map(hobbyist -> {
                    List<Artist> artists = hobbyist.getArtists();
                    int artistsCount = artists.size();
                    return new PageImpl<>(artists, pageable, artistsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }
}
