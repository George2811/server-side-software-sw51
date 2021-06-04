package com.acme.perustars;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.repository.*;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.exception.ResourceNotFoundException;
import com.acme.perustars.service.HobbyistServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class HobbyistServiceImplTest {

    @MockBean
    private HobbyistRepository hobbyistRepository;

    @MockBean
    private SpecialtyRepository specialtyRepository;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private ArtworkRepository artworkRepository;

    @MockBean
    private EventRepository eventRepository;


    @Autowired
    private HobbyistService hobbyistService;



    @TestConfiguration
    static class HobbyistServiceImplTestConfiguration {
        @Bean
        public HobbyistService hobbyistService() {
            return new HobbyistServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Hobbyist By Hobbyist Id With Valid Id Then Returns Hobbyist")
    public void WhenGetHobbyistByHobbyistIdWithValidIdThenReturnsHobbyist() {
        // Arrange
        Long id = 1L;
        Hobbyist hobbyist = new Hobbyist();
        hobbyist.setId(id);
        when(hobbyistRepository.findById(id))
                .thenReturn(Optional.of(hobbyist));

        // Act
        Hobbyist foundHobbyist = hobbyistService.getHobbyistById(id);

        // Assert
        assertThat(foundHobbyist.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When Get Hobbyist By Hobbyist Id With Invalid Id The ReturnsException")
    public void WhenGetHobbyistByHobbyistIdWithInvalidIdTheReturnsException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(hobbyistRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Hobbyist", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Hobbyist hobbyist = hobbyistService.getHobbyistById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}

