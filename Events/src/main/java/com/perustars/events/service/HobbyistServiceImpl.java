package com.perustars.events.service;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.model.Artwork;
import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.model.Specialty;
import com.perustars.events.domain.repository.ArtistRepository;
import com.perustars.events.domain.repository.ArtworkRepository;
import com.perustars.events.domain.repository.HobbyistRepository;
import com.perustars.events.domain.repository.SpecialtyRepository;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class HobbyistServiceImpl implements HobbyistService {
    @Autowired
    private HobbyistRepository hobbyistRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtworkRepository artworkRepository;

    @Override
    public Page<Hobbyist> getAllHobbyist(Pageable pageable) {
        return hobbyistRepository.findAll(pageable);
    }

    @Override
    public Hobbyist getHobbyistById(Long hobbyistId) {
        return hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist createHobbyist(Hobbyist hobbyist) {
        return hobbyistRepository.save(hobbyist);
    }

    @Override
    public Hobbyist updateHobbyist(Long hobbyistId, Hobbyist hobbyistRequest) {
        Hobbyist hobbyist = hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
        hobbyist.setFirstName(hobbyistRequest.getFirstName())
                .setLastName(hobbyistRequest.getLastName());
        return hobbyistRepository.save(hobbyist);
    }

    @Override
    public ResponseEntity<?> deleteHobbyist(Long hobbyistId) {
        Hobbyist hobbyist = hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
        hobbyistRepository.delete(hobbyist);
        return ResponseEntity.ok().build();
    }

    @Override
    public Hobbyist associateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", specialtyId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.selectSpecialty(specialty))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", specialtyId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.deselectSpecialty(specialty))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist associateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.followArtist(artist))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.unfollowArtist(artist))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist associateHobbyistWithArtwork(Long hobbyistId, Long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new ResourceNotFoundException("Artwork", "Id", artworkId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.selectArtwork(artwork))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithArtwork(Long hobbyistId, Long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new ResourceNotFoundException("Artwork", "Id", artworkId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.deselectArtwork(artwork))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }
}
