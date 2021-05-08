package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class HobbyistEventsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @PostMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource associateHobbyistWithEvent(Long hobbyistId, Long eventId){
        return convertToResource(hobbyistService.associateHobbyistWithEvent(hobbyistId, eventId));
    }



    @DeleteMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource disassociateHobbyistWithEvent(Long hobbyistId, Long eventId){
        return convertToResource(hobbyistService.disassociateHobbyistWithEvent(hobbyistId, eventId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
