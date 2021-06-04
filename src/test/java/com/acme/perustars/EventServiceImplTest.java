package com.acme.perustars;


import com.acme.perustars.domain.model.Artwork;
import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.repository.ArtistRepository;
import com.acme.perustars.domain.repository.EventRepository;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.service.EventService;
import com.acme.perustars.exception.ResourceNotFoundException;
import com.acme.perustars.service.EventServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class EventServiceImplTest {
    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private HobbyistRepository hobbyistRepository;

    @Autowired
    private EventService eventservice;

    @TestConfiguration
    static class EventServiceImplTestConfiguration {
        @Bean
        public EventService eventService(){
            return new EventServiceImpl();}
    }

    @Test
    @DisplayName("when GetEventByTitle With Valid Title Then Returns Event")
    public void whenGetEventByTitleWithValidTitleThenReturnsEvent(){
        //Arrange
        Pageable pageable = Pageable.unpaged();
        String title = "Concert";
        List<Event> events = List.of(
                new Event().setId(1L).setTitle(title),
                new Event().setId(2L).setTitle(title),
                new Event().setId(3L).setTitle(title),
                new Event().setId(4L).setTitle(title)
        );

        Page<Event> eventPage = new PageImpl<>(events,pageable,events.size());
        when(eventRepository.findByTitle(title,pageable)).thenReturn(eventPage);

        //Act
        Page<Event> eventsFound = eventservice.getAllEventsByTitle(title, pageable);

        //Assert
        assertThat(eventsFound).isEqualTo(eventPage).contains(events.get(0),events.get(1),events.get(2),events.get(3));
    }

    @Test
    @DisplayName("When getEventByIdAndArtistId With Valid Ids Then Returns Event")
    public void whenGetEventByIdAndArtistIdWithValidIdsThenReturnsEvent(){
        // Arrange
        Long eventId =  1L;
        Long artistId =  1L;
        Event event = new Event()
                .setId(eventId);
        when(eventRepository.findByIdAndArtistId(eventId,artistId)).thenReturn(Optional.ofNullable(event));
        // Act
        Event foundEvent = eventservice.getEventByIdAndArtistId(artistId, eventId);
        // Assert
        assertThat(foundEvent.getId()).isEqualTo(eventId);
    }
    @Test
    @DisplayName("When getEventByIdAndArtistId With Invalid Ids Then Returns ResourceNotFoundExceptions")
    public void whenGetEventByIdAndArtistIdWithInvalidIdsThenReturnsResourceNotFoundExceptions(){
        // Arrange
        Long eventId =  1L;
        Long artistId =  1L;
        String template = "Event not found with Id %s and ArtistId %s";
        Event event = new Event()
                .setId(eventId);
        when(eventRepository.findByIdAndArtistId(eventId,artistId)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, artistId, eventId);
        // Act
        Throwable exception = catchThrowable(()->{
            Event foundEvent = eventservice.getEventByIdAndArtistId(artistId, eventId);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
