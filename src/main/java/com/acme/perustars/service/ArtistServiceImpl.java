package com.acme.perustars.service;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.repository.ArtistRepository;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.repository.SpecialtyRepository;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.exception.ResourceNotFoundException;
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
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private HobbyistRepository hobbyistRepository;

    @Override
    public Page<Artist> getAllArtists(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id",
                artistId));
    }

    @Override
    public Artist createArtist(Artist artist) {
        if (!specialtyRepository.existsByName(artist.getSpecialty().getName()))
            throw new ResourceNotFoundException("Specialty", "Name", artist.getSpecialty().getName());

        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtist(Long artistId, Artist artistRequest) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist",
                "Id", artistId));

        if (!specialtyRepository.existsByName(artistRequest.getSpecialty().getName()))
            throw new ResourceNotFoundException("Specialty", "Name", artistRequest.getSpecialty().getName());

        artist.setBrandName(artistRequest.getBrandName())
                .setDescription(artistRequest.getDescription())
                .setPhrase(artistRequest.getPhrase())
                .setSpecialty(artistRequest.getSpecialty())
                .setFirstName(artistRequest.getFirstName())
                .setLastName(artistRequest.getLastName());
        return artistRepository.save(artist);
    }

    @Override
    public ResponseEntity<?> deleteArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist",
                "Id", artistId));
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
                    List<Artist> artists = hobbyist.getFollows();
                    int artistsCount = artists.size();
                    return new PageImpl<>(artists, pageable, artistsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }
}
