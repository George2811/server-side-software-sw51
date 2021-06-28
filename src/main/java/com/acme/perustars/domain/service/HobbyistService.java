package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.Hobbyist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface HobbyistService {
    Page<Hobbyist> getAllHobbyists(Pageable pageable);
    Hobbyist getHobbyistById(Long hobbyistId);
    Hobbyist getHobbyistByUserId(Long userId);
    Hobbyist createHobbyist(Hobbyist hobbyist);
    Hobbyist updateHobbyist(Long hobbyistId, Hobbyist hobbyistRequest);
    ResponseEntity<?> deleteHobbyist(Long hobbyistId);
    Hobbyist associateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId);
    Hobbyist disassociateHobbyistWithSpecialty(Long hobbyistId, Long specialtyId);
    Hobbyist associateHobbyistWithArtist(Long hobbyistId, Long artistId);
    Hobbyist disassociateHobbyistWithArtist(Long hobbyistId, Long artistId);
    Hobbyist associateHobbyistWithArtwork(Long hobbyistId, Long artworkId);
    Hobbyist disassociateHobbyistWithArtwork(Long hobbyistId, Long artworkId);
    Hobbyist associateHobbyistWithEvent(Long hobbyistId, Long eventId);
    Hobbyist disassociateHobbyistWithEvent(Long hobbyistId, Long eventId);
}
