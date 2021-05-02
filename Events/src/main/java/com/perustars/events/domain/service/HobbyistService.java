package com.perustars.events.domain.service;

import com.perustars.events.domain.model.Hobbyist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface HobbyistService {
    Page<Hobbyist> getAllHobbyist(Pageable pageable);
    Hobbyist getHobbyistById(Long hobbyistId);
    Hobbyist createHobbyist(Hobbyist hobbyist);
    Hobbyist updateHobbyist(Long hobbyistId, Hobbyist hobbyistRequest);
    ResponseEntity<?> deleteHobbyist(Long hobbyistId);
}
