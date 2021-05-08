package com.acme.perustars.service;

import com.perustars.events.domain.model.*;
import com.perustars.events.domain.repository.*;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HobbyistServiceImpl implements HobbyistService {
    @Autowired
    private HobbyistRepository hobbyistRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Hobbyist> getAllHobbyists(Pageable pageable) {
        return hobbyistRepository.findAll(pageable);
    }

    @Override
    public Hobbyist getHobbyistById(Long hobbyistId) {
        return hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException("Hobbyist",
                "Id", hobbyistId));
    }

    @Override
    public Hobbyist createHobbyist(Hobbyist hobbyist) {
        return hobbyistRepository.save(hobbyist);
    }

    @Override
    public Hobbyist updateHobbyist(Long hobbyistId, Hobbyist hobbyistRequest) {
        Hobbyist hobbyist = hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException(
                "Hobbyist", "Id", hobbyistId));
        hobbyist.setFirstName(hobbyistRequest.getFirstName())
                .setLastName(hobbyistRequest.getLastName());
        return hobbyistRepository.save(hobbyist);
    }

    @Override
    public ResponseEntity<?> deleteHobbyist(Long hobbyistId) {
        Hobbyist hobbyist = hobbyistRepository.findById(hobbyistId).orElseThrow(() -> new ResourceNotFoundException(
                "Hobbyist", "Id", hobbyistId));
        hobbyistRepository.delete(hobbyist);
        return ResponseEntity.ok().build();
    }

    @Override
    public Hobbyist associateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId) {
        Specialty specialty =
                specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty"
                        , "Id", specialtyId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.selectSpecialty(specialty))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId) {
        Specialty specialty =
                specialtyRepository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Specialty"
                        , "Id", specialtyId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.deselectSpecialty(specialty))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist associateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist",
                "Id", artistId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.followArtist(artist))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist",
                "Id", artistId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.unfollowArtist(artist))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist associateHobbyistWithArtwork(Long hobbyistId, Long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new ResourceNotFoundException(
                "Artwork", "Id", artworkId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.selectArtwork(artwork))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithArtwork(Long hobbyistId, Long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(() -> new ResourceNotFoundException(
                "Artwork", "Id", artworkId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.deselectArtwork(artwork))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist associateHobbyistWithEvent(Long hobbyistId, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event", "Id"
                , eventId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.subscribeEvent(event))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Hobbyist disassociateHobbyistWithEvent(Long hobbyistId, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event", "Id"
                , eventId));
        return hobbyistRepository.findById(hobbyistId).map(hobbyist -> hobbyistRepository.save(hobbyist.unsubscribeEvent(event))).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }
}
