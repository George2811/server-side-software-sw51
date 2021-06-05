package com.acme.perustars.unit;


import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.repository.EventRepository;
import com.acme.perustars.domain.service.EventService;
import com.acme.perustars.service.EventServiceImpl;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class EventServiceImplTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventService eventservice;

    @TestConfiguration
    static class EventServiceImplTestConfiguration {
        @Bean
        public EventService eventService(){
            return new EventServiceImpl();}
    }
    public void whenGetEventByTitleWithValidTitleThenReturnsEvent(){
        //Arrange
        String title = "Big Event";
        Event event = new Event()
                .setId(1L)
                .setTitle(title);

        //Act
       // Event foundEvent = eventservice.getAllEventsByTitle(title, pageable);

        //Assert
       // assertThat(foundEvent.getCost()).isEqualTo(title);
    }
}